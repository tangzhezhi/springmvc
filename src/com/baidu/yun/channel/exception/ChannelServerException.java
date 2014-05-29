package com.baidu.yun.channel.exception;

public class ChannelServerException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = 216042651216631503L;

    private long requestId;

    private int errorCode;

    private String errorMsg = null;

    public ChannelServerException() {

    }

    public ChannelServerException(long requestId, int errorCode, String errorMsg) {
        this.requestId = requestId;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

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

}
