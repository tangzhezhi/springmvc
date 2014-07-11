/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.dto.attendance;
import org.apache.log4j.Logger;

public class AttendanceStateDetailDTO {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	
    private java.lang.String id;
    private java.lang.String pid;
    private java.lang.String attendanceTime;
    private java.lang.String attendanceName;
    private java.lang.String state;
    private java.lang.String attendanceRuleId;
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String id) {
		this.id = id;
	}
	public java.lang.String getPid() {
		return this.pid;
	}
	
	public void setPid(java.lang.String pid) {
		this.pid = pid;
	}
	public java.lang.String getAttendanceTime() {
		return this.attendanceTime;
	}
	
	public void setAttendanceTime(java.lang.String attendanceTime) {
		this.attendanceTime = attendanceTime;
	}
	public java.lang.String getAttendanceName() {
		return this.attendanceName;
	}
	
	public void setAttendanceName(java.lang.String attendanceName) {
		this.attendanceName = attendanceName;
	}
	public java.lang.String getState() {
		return this.state;
	}
	
	public void setState(java.lang.String state) {
		this.state = state;
	}
	public java.lang.String getAttendanceRuleId() {
		return this.attendanceRuleId;
	}
	
	public void setAttendanceRuleId(java.lang.String attendanceRuleId) {
		this.attendanceRuleId = attendanceRuleId;
	}

}
