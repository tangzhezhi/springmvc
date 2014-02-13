/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.dto.system;
import org.apache.log4j.Logger;

public class DepartmentDTO {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	
    private java.lang.String departmentid;
    private java.lang.String orgid;
    private java.lang.String departmentname;
    private String pdepartmentid;
    private String pdepartmentname;
    private String orgname;
	
	public java.lang.String getDepartmentid() {
		return this.departmentid;
	}
	
	public void setDepartmentid(java.lang.String departmentid) {
		this.departmentid = departmentid;
	}
	public java.lang.String getOrgid() {
		return this.orgid;
	}
	
	public void setOrgid(java.lang.String orgid) {
		this.orgid = orgid;
	}
	public java.lang.String getDepartmentname() {
		return this.departmentname;
	}
	
	public void setDepartmentname(java.lang.String departmentname) {
		this.departmentname = departmentname;
	}

	public String getPdepartmentid() {
		return pdepartmentid;
	}

	public void setPdepartmentid(String pdepartmentid) {
		this.pdepartmentid = pdepartmentid;
	}

	public String getPdepartmentname() {
		return pdepartmentname;
	}

	public void setPdepartmentname(String pdepartmentname) {
		this.pdepartmentname = pdepartmentname;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}
	

}
