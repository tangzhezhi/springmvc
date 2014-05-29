package com.baidu.yun.core.log;

public class YunLogEvent {

    public static final int FATAL = 0;
    public static final int WARNING = 1;
    public static final int NOTICE = 2;
    public static final int INFO = 3;
    public static final int DEBUG = 4;

    private int level;

    private String message;

    public YunLogEvent(int level, String message) {
        this.level = level;
        this.message = message;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
