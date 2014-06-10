package org.tang.jpa.dto.mobile;

public class MobileBaseRepDTO {
	
	private String sessionKey;
	private int msgFlag;
	private String response;
	
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public int getMsgFlag() {
		return msgFlag;
	}
	public void setMsgFlag(int msgFlag) {
		this.msgFlag = msgFlag;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
}
