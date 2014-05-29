package com.baidu.yun.core.model;

import java.util.Map;

import com.baidu.yun.core.annotation.JSonPath;

public class ErrorResponse {

    @JSonPath(path = "request_id")
    private long requestId = 0;

    @JSonPath(path = "error_code")
    private int errorCode = 0;

    @JSonPath(path = "error_msg")
    private String errorMsg = null;

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean validate() {
        return this.errorMsg != null && this.errorCode != 0;
    }

    public void buildFromMap(Map map) {
        construct(map, 0);
    }

    private void construct(Map map, int depth) {
        if (depth >= 2) {
            return;
        }
        for (Object key : map.entrySet()) {
            Object value = map.get(key);
            if (value instanceof Map) {
                construct((Map) value, depth + 1);
            } else {
                String keyName = key.toString().trim();
                if (requestId == 0 && keyName.equalsIgnoreCase("request_id")
                        && (value instanceof Long || value instanceof Integer)) {
                    requestId = Long.parseLong(value.toString());
                } else if (errorCode == 0
                        && keyName.equalsIgnoreCase("error_code")
                        && (value instanceof Integer || value instanceof Long)) {
                    errorCode = Integer.parseInt(value.toString());
                } else if (errorMsg != null
                        && keyName.equalsIgnoreCase("error_msg")
                        && value instanceof String) {
                    errorMsg = (String) value;
                }
            }
        }
    }

}
