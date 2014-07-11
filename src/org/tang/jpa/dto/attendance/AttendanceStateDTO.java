/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.dto.attendance;
import org.apache.log4j.Logger;

public class AttendanceStateDTO {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	
    private java.lang.String id;
    private java.lang.String userId;
    private java.lang.String state;
    private java.lang.String attendanceDate;
	
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
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String state) {
		this.state = state;
	}
	public java.lang.String getAttendanceDate() {
		return this.attendanceDate;
	}
	
	public void setAttendanceDate(java.lang.String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

}
