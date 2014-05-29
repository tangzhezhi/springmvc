package com.baidu.yun.core.event;

public class YunHttpEvent {

    public static final int NETWORK_TALK_SUCCESS = 0;
    public static final int NETWORK_NORMAL = 1;

    public static final int TCP_CONNECT_FAIL = 10001;
    public static final int NETWORK_IO_EXCEPTION = 10002;
    public static final int NETWORK_TIMEOUT_EXCEPTION = 10003;
    public static final int NETWORK_TRY_TIMES_EXCEPTION = 10004;

    public static final int OK = 20000;

    private int eventType;

    private int httpStatusCode;

    private String url;

    private String params;

    private String response;

    public YunHttpEvent() {
    }

    public YunHttpEvent(int eventType, String url, String params,
            int httpStatusCode, String response) {
        this.eventType = eventType;
        this.httpStatusCode = httpStatusCode;
        this.url = url;
        this.params = params;
        this.response = response;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

}
