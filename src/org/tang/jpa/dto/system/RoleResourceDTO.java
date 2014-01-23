package org.tang.jpa.dto.system;


public class RoleResourceDTO {
	
	private String currentroleid;
	
	private String roleid;
	
	private String resourceid;

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getResourceid() {
		return resourceid;
	}

	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}

	public String getCurrentroleid() {
		return currentroleid;
	}

	public void setCurrentroleid(String currentroleid) {
		this.currentroleid = currentroleid;
	}
	
}
