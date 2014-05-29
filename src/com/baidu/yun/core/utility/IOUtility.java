package com.baidu.yun.core.utility;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.omg.CORBA_2_3.portable.OutputStream;

public class IOUtility {

    public static String readContentFromReader(BufferedReader reader)
            throws IOException {
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append('\n');
        }
        return sb.toString();
    }

    public static String readContentFromInputStream(InputStream is)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] datas = new byte[1024];
        int len = 0;
        while ((len = is.read(datas, 0, datas.length)) != -1) {
            baos.write(datas, 0, len);
        }
        return baos.toString();
    }

    public static byte[] readBytesFromInputStream(InputStream is)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] datas = new byte[1024];
        int len = 0;
        while ((len = is.read(datas, 0, datas.length)) != -1) {
            baos.write(datas, 0, len);
        }
        return baos.toByteArray();
    }

    public static void closeInputStream(InputStream is) {
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeOuputStream(OutputStream os) {
        try {
            if (os != null) {
                os.flush();
                os.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
