/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.dto.mobile;
import org.apache.log4j.Logger;

public class ChatMsgDTO {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	
    private java.lang.String id;
    private java.lang.String fromUserId;
    private java.lang.String toUserId;
    private java.lang.String content;
    private java.lang.String createTime;
    private java.lang.String state;
    private java.lang.String sendTime;
    private String pushuserId;
    private String pushChannelId;
    private String deviceType;
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getFromUserId() {
		return this.fromUserId;
	}
	
	public void setFromUserId(java.lang.String fromUserId) {
		this.fromUserId = fromUserId;
	}
	public java.lang.String getToUserId() {
		return this.toUserId;
	}
	
	public void setToUserId(java.lang.String toUserId) {
		this.toUserId = toUserId;
	}
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	public java.lang.String getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String state) {
		this.state = state;
	}
	public java.lang.String getSendTime() {
		return this.sendTime;
	}
	
	public void setSendTime(java.lang.String sendTime) {
		this.sendTime = sendTime;
	}

	public String getPushuserId() {
		return pushuserId;
	}

	public void setPushuserId(String pushuserId) {
		this.pushuserId = pushuserId;
	}

	public String getPushChannelId() {
		return pushChannelId;
	}

	public void setPushChannelId(String pushChannelId) {
		this.pushChannelId = pushChannelId;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

}
