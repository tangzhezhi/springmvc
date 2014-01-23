package org.tang.jpa.dto.system;

/**
 * 操作日志
 * @author lenovo
 *
 */
public class OperateLogDTO {
	
	private String logId;
	private String userId;
	private String operateObject;
	private String operateMethod;
	private String operateDate;
	private String operateTime;
	private String orgId;
	private String state; //0：失败、1:成功
	
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOperateMethod() {
		return operateMethod;
	}
	public void setOperateMethod(String operateMethod) {
		this.operateMethod = operateMethod;
	}
	public String getOperateDate() {
		return operateDate;
	}
	public void setOperateDate(String operateDate) {
		this.operateDate = operateDate;
	}
	public String getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getOperateObject() {
		return operateObject;
	}
	public void setOperateObject(String operateObject) {
		this.operateObject = operateObject;
	}

}
