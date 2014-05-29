package com.baidu.yun.channel.auth.signature;

import java.util.Map;

import com.baidu.yun.core.utility.MessageDigestUtility;

public class ChannelSignatureDigest {

    public static final String URL_KEY = "url";
    public static final String HTTP_METHOD_KEY = "http_method";
    public static final String CLIENT_SECRET_KEY = "client_secret";

    // private static final Set<String> PARAM_SET = new TreeSet<String>();

    public String digest(String accessKey, String secretKey,
            Map<String, String> params) {
        return null;
    }

    public String digest(String method, String url, String clientSecret,
            Map<String, String> params) {
        StringBuilder sb = new StringBuilder();
        sb.append(method).append(url);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            if (URL_KEY.equals(key) || HTTP_METHOD_KEY.equals(key)) {
                continue;
            } else {
                sb.append(entry.getKey()).append('=').append(entry.getValue());
            }
        }
        sb.append(clientSecret);
        String encodeString = MessageDigestUtility.urlEncode(sb.toString());
        if (encodeString != null) {
            encodeString = encodeString.replaceAll("\\*", "%2A");
        }
        return MessageDigestUtility.toMD5Hex(encodeString);
    }

    public boolean checkParams(Map<String, String> params) {
        return false;
    }

}
