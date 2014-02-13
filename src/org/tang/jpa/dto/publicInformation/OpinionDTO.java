/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.dto.publicInformation;
import org.apache.log4j.Logger;

public class OpinionDTO {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	
    private java.lang.String opinionid;
    private java.lang.String opinionTitle;
    private java.lang.String userid;
    private java.lang.String createtime;
    private java.lang.String approveState;
    private java.lang.String opinionContent;
    private java.lang.String approveUserid;
    private java.lang.String approveTime;
	
	public java.lang.String getOpinionid() {
		return this.opinionid;
	}
	
	public void setOpinionid(java.lang.String opinionid) {
		this.opinionid = opinionid;
	}
	public java.lang.String getOpinionTitle() {
		return this.opinionTitle;
	}
	
	public void setOpinionTitle(java.lang.String opinionTitle) {
		this.opinionTitle = opinionTitle;
	}
	public java.lang.String getUserid() {
		return this.userid;
	}
	
	public void setUserid(java.lang.String userid) {
		this.userid = userid;
	}
	public java.lang.String getCreatetime() {
		return this.createtime;
	}
	
	public void setCreatetime(java.lang.String createtime) {
		this.createtime = createtime;
	}
	public java.lang.String getApproveState() {
		return this.approveState;
	}
	
	public void setApproveState(java.lang.String approveState) {
		this.approveState = approveState;
	}
	public java.lang.String getOpinionContent() {
		return this.opinionContent;
	}
	
	public void setOpinionContent(java.lang.String opinionContent) {
		this.opinionContent = opinionContent;
	}
	public java.lang.String getApproveUserid() {
		return this.approveUserid;
	}
	
	public void setApproveUserid(java.lang.String approveUserid) {
		this.approveUserid = approveUserid;
	}
	public java.lang.String getApproveTime() {
		return this.approveTime;
	}
	
	public void setApproveTime(java.lang.String approveTime) {
		this.approveTime = approveTime;
	}

}
