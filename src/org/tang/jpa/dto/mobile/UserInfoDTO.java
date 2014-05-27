package org.tang.jpa.dto.mobile;

public  class UserInfoDTO  {
	private String userId = "";
	private String userName = "";
	private int sex = 0;
	private String phone = "";
	private String picUrl = "";
	private String userType;
	private String orgId;
	private String departId;
	
	private String pushUserId;
	
	private String pushChannelId;
	
	private String deviceType;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	
	public String getPushUserId() {
		return pushUserId;
	}
	public void setPushUserId(String pushUserId) {
		this.pushUserId = pushUserId;
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
	@Override
	public String toString() {
		return "UserInfoDTO [departId=" + departId + ", deviceType="
				+ deviceType + ", orgId=" + orgId + ", phone=" + phone
				+ ", picUrl=" + picUrl + ", pushChannelId=" + pushChannelId
				+ ", pushUserId=" + pushUserId + ", sex=" + sex + ", userId="
				+ userId + ", userName=" + userName + ", userType=" + userType
				+ "]";
	}
	
}
