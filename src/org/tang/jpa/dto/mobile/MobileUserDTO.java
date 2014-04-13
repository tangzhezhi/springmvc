package org.tang.jpa.dto.mobile;

public class MobileUserDTO {
	
	private String userId;
	private String userName;
	private String userPwd;
	private int userType;
	
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
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	@Override
	public String toString() {
		return "MobileUserDTO [userId=" + userId + ", userName=" + userName
				+ ", userType=" + userType + "]";
	}
	
}
