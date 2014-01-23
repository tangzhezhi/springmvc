/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.dto.system;
import org.apache.log4j.Logger;

public class OrganizationDTO {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	
    private java.lang.String orgid;
    private java.lang.String orgname;
    private java.lang.String orgaddress;
    private java.lang.String orgcontact;
    private java.lang.String orgphone;
    private java.lang.String orgemails;
	
	public java.lang.String getOrgid() {
		return this.orgid;
	}
	
	public void setOrgid(java.lang.String orgid) {
		this.orgid = orgid;
	}
	public java.lang.String getOrgname() {
		return this.orgname;
	}
	
	public void setOrgname(java.lang.String orgname) {
		this.orgname = orgname;
	}
	public java.lang.String getOrgaddress() {
		return this.orgaddress;
	}
	
	public void setOrgaddress(java.lang.String orgaddress) {
		this.orgaddress = orgaddress;
	}
	public java.lang.String getOrgcontact() {
		return this.orgcontact;
	}
	
	public void setOrgcontact(java.lang.String orgcontact) {
		this.orgcontact = orgcontact;
	}
	public java.lang.String getOrgphone() {
		return this.orgphone;
	}
	
	public void setOrgphone(java.lang.String orgphone) {
		this.orgphone = orgphone;
	}
	public java.lang.String getOrgemails() {
		return this.orgemails;
	}
	
	public void setOrgemails(java.lang.String orgemails) {
		this.orgemails = orgemails;
	}

}
