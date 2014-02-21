/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.dto.publicInformation;
import org.apache.log4j.Logger;
import java.util.List;
import org.springframework.core.io.AbstractResource;

public class EmailDTO {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	
    private java.lang.String emailid;
    private java.lang.String fromadd;
    private java.lang.String subject;
    private java.lang.String content;
    private java.lang.String status;
    private java.lang.String toadd;
    private java.lang.String attchfileurl;
    
    
    //附件
    private List<AbstractResource> resources;
    
	public java.lang.String getEmailid() {
		return this.emailid;
	}
	
	public void setEmailid(java.lang.String emailid) {
		this.emailid = emailid;
	}
	public java.lang.String getFromadd() {
		return this.fromadd;
	}
	
	public void setFromadd(java.lang.String fromadd) {
		if(fromadd.equals("") || null == fromadd){
			fromadd = "tangzhezi@126.com";
		}
		this.fromadd = fromadd;
	}
	public java.lang.String getSubject() {
		return this.subject;
	}
	
	public void setSubject(java.lang.String subject) {
		this.subject = subject;
	}
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	public java.lang.String getStatus() {
		return this.status;
	}
	
	public void setStatus(java.lang.String status) {
		this.status = status;
	}
	public java.lang.String getToadd() {
		return this.toadd;
	}
	
	public void setToadd(java.lang.String toadd) {
		this.toadd = toadd;
	}
	public java.lang.String getAttchfileurl() {
		return this.attchfileurl;
	}
	
	public void setAttchfileurl(java.lang.String attchfileurl) {
		this.attchfileurl = attchfileurl;
	}
	
	public List<AbstractResource> getResources() {
		return resources;
	}
	public void setResources(List<AbstractResource> resources) {
		this.resources = resources;
	}

}
