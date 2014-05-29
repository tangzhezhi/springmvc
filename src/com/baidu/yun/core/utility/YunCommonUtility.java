package com.baidu.yun.core.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class YunCommonUtility {

    public static long currentTimeSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    public static String formatFromDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String formatFromDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    public static Date formatFromString(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            // e.printStackTrace();
        }
        return null;
    }

}
