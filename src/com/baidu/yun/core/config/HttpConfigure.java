package com.baidu.yun.core.config;

/**
 * 
 * @author renjie
 * 
 */
public class HttpConfigure {

    public static final int DEFAULT_MAX_RETRY_TIMES = 3;

    public static final int DEFAULT_MAX_TIMEOUT = 10000; // ms

    private int maxRetryTimes = DEFAULT_MAX_RETRY_TIMES;

    private int maxTimeout = DEFAULT_MAX_TIMEOUT;

    private boolean relocationable = true;

    public int getMaxRetryTimes() {
        return maxRetryTimes;
    }

    public void setMaxRetryTimes(int maxRetryTimes) {
        this.maxRetryTimes = maxRetryTimes;
    }

    public int getMaxTimeout() {
        return maxTimeout;
    }

    public void setMaxTimeout(int maxTimeout) {
        this.maxTimeout = maxTimeout;
    }

    public boolean isRelocationable() {
        return relocationable;
    }

    public void setRelocationable(boolean relocationable) {
        this.relocationable = relocationable;
    }

}
