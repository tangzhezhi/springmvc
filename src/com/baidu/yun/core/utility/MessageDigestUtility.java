package com.baidu.yun.core.utility;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestUtility {

    public static String urlEncode(String url) {
        try {
            return URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // e.printStackTrace();
            return URLEncoder.encode(url);
        }
    }

    public static byte[] toMD5(String datas, String charset)
            throws UnsupportedEncodingException {
        return toMD5(datas.getBytes(charset));
    }

    public static String toMD5Hex(String datas) {
        String result = null;
        try {
            result = toMD5Hex(datas, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String toMD5Hex(String datas, String charset)
            throws UnsupportedEncodingException {
        return toMD5Hex(datas.getBytes(charset));
    }

    public static byte[] toMD5(byte[] datas) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("md5");
            return md.digest(datas);
        } catch (NoSuchAlgorithmException e) {
            // throw new BCMSException();
            return null;
        }
    }

    public static String toMD5Hex(byte[] datas) {
        return StringUtility.toHexString(toMD5(datas));
    }

}
