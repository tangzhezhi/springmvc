package com.baidu.yun.core.httpclient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import com.baidu.yun.core.callback.YunHttpObservable;
import com.baidu.yun.core.callback.YunHttpObserver;
import com.baidu.yun.core.config.HttpConfigure;
import com.baidu.yun.core.event.YunHttpEvent;
import com.baidu.yun.core.exception.YunHttpClientException;
import com.baidu.yun.core.model.HttpRestResponse;
import com.baidu.yun.core.utility.IOUtility;
import com.baidu.yun.core.utility.MessageDigestUtility;
import com.baidu.yun.core.utility.Native2AsciiUtility;

public class YunHttpClient implements YunHttpObservable {

    @SuppressWarnings("unused")
    private static Logger logger = Logger.getLogger(YunHttpClient.class
            .getName());

    private List<YunHttpObserver> listeners = Collections
            .synchronizedList(new LinkedList<YunHttpObserver>());

    private HttpConfigure httpConfigure = new HttpConfigure();

    public HttpRestResponse doExecutePostRequestResponse(String surl,
            Map<String, String> params) throws YunHttpClientException {

        HttpURLConnection conn = null;
        InputStream is = null;
        InputStream err = null;

        int httpStatusCode = -1;
        String responseContent = null;
        String paramsContent = concatHttpParams(params);

        String locationUrl = null;
        boolean redirectable = false;

        int maxRetryTimes = 1;
        if (httpConfigure != null) {
            maxRetryTimes = httpConfigure.getMaxRetryTimes();
        }

        for (int i = 0; i < maxRetryTimes; i++) {
            responseContent = null;
            httpStatusCode = -1;
            try {

                URL url = null;
                if (redirectable == false) {
                    url = new URL(surl);
                } else {
                    url = new URL(locationUrl);
                    redirectable = false;
                    locationUrl = null;
                }

                conn = (HttpURLConnection) url.openConnection();
                if (conn instanceof HttpsURLConnection) {
                    ((HttpsURLConnection) conn)
                            .setHostnameVerifier(new HostnameVerifier() {
                                @Override
                                public boolean verify(String hostname,
                                        SSLSession session) {
                                    return true;
                                }
                            });

                    TrustManager[] tm = { new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain,
                                String authType) throws CertificateException {
                            // TODO Auto-generated method stub
                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain,
                                String authType) throws CertificateException {
                            // TODO Auto-generated method stub
                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            // TODO Auto-generated method stub
                            return null;
                        }
                    } };
                    SSLContext sslContext = SSLContext.getInstance("SSL");
                    sslContext.init(null, tm, new java.security.SecureRandom());
                    SSLSocketFactory ssf = sslContext.getSocketFactory();
                    ((HttpsURLConnection) conn).setSSLSocketFactory(ssf);
                }

                if (httpConfigure != null) {
                    conn.setConnectTimeout(httpConfigure.getMaxTimeout());
                    conn.setReadTimeout(httpConfigure.getMaxTimeout());
                    if (httpConfigure.isRelocationable()) {
                        conn.setInstanceFollowRedirects(true);
                    }
                }

                conn.setRequestMethod("POST");
                configureConnection(conn);
                conn.connect();

            } catch (Exception e) {
                // TODO: handle exception

                conn.disconnect();

                if (i + 1 >= maxRetryTimes) {

                    // @notify log event
                    notifyAndCallback(new YunHttpEvent(
                            YunHttpEvent.TCP_CONNECT_FAIL, surl, paramsContent,
                            httpStatusCode,
                            "Network connect fail, domain exists?"));

                    throw new YunHttpClientException(
                            "Network connect fail, domain exists?");

                }
                continue;

            }

            try {

                OutputStream os = conn.getOutputStream();
                os.write(paramsContent.getBytes());
                os.flush();
                os.close();

                httpStatusCode = conn.getResponseCode();

                if (httpStatusCode == 200) {

                    is = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(is));
                    responseContent = IOUtility.readContentFromReader(reader);

                    // @notify log event
                    notifyAndCallback(new YunHttpEvent(
                            YunHttpEvent.NETWORK_TALK_SUCCESS, surl,
                            paramsContent, httpStatusCode, responseContent));

                    HttpRestResponse response = new HttpRestResponse();
                    response.setHttpStatusCode(httpStatusCode);
                    if (responseContent != null) {
                        responseContent = Native2AsciiUtility
                                .ascii2Native(responseContent);
                    }
                    response.setJsonResponse(responseContent);
                    return response;

                } else if (httpStatusCode == 301 || httpStatusCode == 302) {
                    // relocation
                    if (httpConfigure != null
                            && httpConfigure.isRelocationable()) {
                        String location = conn.getHeaderField("location");
                        if (location != null) {
                            redirectable = true;
                            locationUrl = location;
                            i--;

                            // @notify log event
                            notifyAndCallback(new YunHttpEvent(
                                    YunHttpEvent.NETWORK_NORMAL, surl,
                                    paramsContent, httpStatusCode,
                                    "redirect to " + locationUrl));

                        } else {
                            // @notify log event, notify header location is
                            // null, so give up to
                            notifyAndCallback(new YunHttpEvent(
                                    YunHttpEvent.NETWORK_NORMAL, surl,
                                    paramsContent, httpStatusCode,
                                    "header location is null, so give up to redirect"));
                        }
                    } else {
                        // @notify log event, redirect location event....
                        notifyAndCallback(new YunHttpEvent(
                                YunHttpEvent.NETWORK_NORMAL, surl,
                                paramsContent, httpStatusCode,
                                "web server suggest redirect, but client don't support it"));
                    }
                    continue;
                } else {
                    err = conn.getErrorStream();
                    responseContent = IOUtility.readContentFromInputStream(err);

                    // @notify log event
                    notifyAndCallback(new YunHttpEvent(
                            YunHttpEvent.NETWORK_NORMAL, surl, paramsContent,
                            httpStatusCode, responseContent));

                    HttpRestResponse response = new HttpRestResponse();
                    response.setHttpStatusCode(httpStatusCode);
                    if (responseContent != null) {
                        responseContent = Native2AsciiUtility
                                .ascii2Native(responseContent);
                    }
                    response.setJsonResponse(responseContent);
                    return response;

                }
            } catch (Exception e) {
                // TODO Auto-generated catch block

                // @notify log event
                notifyAndCallback(new YunHttpEvent(
                        YunHttpEvent.NETWORK_IO_EXCEPTION, surl, paramsContent,
                        httpStatusCode, e.getMessage()));
                continue;

            } finally {
                IOUtility.closeInputStream(is);
                IOUtility.closeInputStream(err);
                if (conn != null) {
                    conn.disconnect();
                }
            }
        }

        // retry time more than threshold
        httpStatusCode = 404;
        responseContent = "Connect web server fail, retry time more than threshold";

        // @notify log event
        notifyAndCallback(new YunHttpEvent(
                YunHttpEvent.NETWORK_TRY_TIMES_EXCEPTION, surl, paramsContent,
                httpStatusCode, responseContent));

        throw new YunHttpClientException("Network connect fail, domain exists?");

    }

    private void configureConnection(HttpURLConnection conn) {
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        conn.setRequestProperty("User-Agent",
                "Yun/Java API Client(www.baidu.com)");
    }

    private String concatHttpParams(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = false;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (isFirst == false) {
                isFirst = true;
            } else {
                sb.append('&');
            }
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(MessageDigestUtility.urlEncode(entry.getValue()));
        }
        return sb.toString();
    }

    public void addHttpCallback(YunHttpObserver callback) {
        if (!listeners.contains(callback)) {
            listeners.add(callback);
        }
    }

    public void addBatchHttpCallBack(List<YunHttpObserver> callbacks) {
        // TODO Auto-generated method stub
        for (YunHttpObserver callback : callbacks) {
            addHttpCallback(callback);
        }
    }

    public void removeCallBack(YunHttpObserver callback) {
        listeners.remove(callback);
    }

    public void notifyAndCallback(YunHttpEvent event) {
        Iterator<YunHttpObserver> iter = listeners.iterator();
        while (iter.hasNext()) {
            YunHttpObserver callback = iter.next();
            callback.onHandle(event);
        }
    }

}
