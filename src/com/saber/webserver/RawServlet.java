package com.saber.webserver;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RawServlet {

    private byte[] rawData = null;

    private String token = null;

    public RawServlet(byte[] rawData) {
        super();
        this.rawData = rawData;
    }

    public void doGet(InputStream is, OutputStream os) {
        doPost(is, os);
    }

    public void doPost(InputStream is, OutputStream os) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {

            byte[] buffer = new byte[1024];
            int len = 0;
            if ((len = is.read(buffer)) != -1) {
                // System.out.println("...." + len);
                baos.write(buffer, 0, len);
            }
            System.out.println("http request:" + baos.toString());

            token = fetchToken(baos.toString());

            os.write(rawData);
            os.flush();
            os.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String fetchToken(String http) {
        Pattern pattern = Pattern.compile("token=([a-zA-Z0-9]*)");
        Matcher matcher = pattern.matcher(http);
        while (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // public static void main(String[] args) {
    // RawServlet servlet = new RawServlet("".getBytes());
    // String token =
    // servlet.fetchToken("http request:GET /bcms/accept_sub?destination=http://127.0.0.1:8080/rest&queue_name=24d27fa9a5451eb93ff8755cff90bb63&token=3a6ac580aec6e21902a9e00ac5dece61 HTTP/1.1");
    // System.out.println(token);
    // }

}
