/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.dto.mobile;

public class TaskWeeklyDTO {

    private java.lang.String id;
    private String orgId;
    private java.lang.String authorId;
    private java.lang.String authorName;
    private java.lang.String type;
    private java.lang.String title;
    private java.lang.String content;
    private java.lang.String createTime;
    private String createDate;
    private String receiveId;
	
	public java.lang.String getId() {
		return this.id;
	}
	
	public void setId(java.lang.String id) {
		this.id = id;
	}
	
	public java.lang.String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(java.lang.String authorId) {
		this.authorId = authorId;
	}

	public java.lang.String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(java.lang.String authorName) {
		this.authorName = authorName;
	}

	public java.lang.String getType() {
		return this.type;
	}
	
	public void setType(java.lang.String type) {
		this.type = type;
	}
	public java.lang.String getTitle() {
		return this.title;
	}
	
	public void setTitle(java.lang.String title) {
		this.title = title;
	}
	public java.lang.String getContent() {
		return this.content;
	}
	
	public void setContent(java.lang.String content) {
		this.content = content;
	}
	
	public java.lang.String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}

}
