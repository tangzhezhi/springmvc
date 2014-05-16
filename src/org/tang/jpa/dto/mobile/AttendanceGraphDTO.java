/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.dto.mobile;
public class AttendanceGraphDTO {

	
    private java.lang.String id;
    private java.lang.String userId;
    private java.lang.String userName;
    private java.lang.String createTime;
    private java.lang.String gps;
    private java.lang.String address;
	private String latitude;
	private String longitude;
    private String photoUrl;
    
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	public java.lang.String getUserName() {
		return this.userName;
	}
	
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}
	public java.lang.String getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}
	public java.lang.String getGps() {
		return this.gps;
	}
	
	public void setGps(java.lang.String gps) {
		this.gps = gps;
	}
	public java.lang.String getAddress() {
		return this.address;
	}
	
	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

}
