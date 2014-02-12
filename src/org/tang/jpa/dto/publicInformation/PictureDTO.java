/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.dto.publicInformation;
import org.apache.log4j.Logger;

public class PictureDTO {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	
    private java.lang.String pictureid;
    private java.lang.String picName;
    private java.lang.String picUrl;
    private java.lang.String userId;
    private java.lang.String createTime;
	private String articleId;
    
	public java.lang.String getPictureid() {
		return this.pictureid;
	}
	
	public void setPictureid(java.lang.String pictureid) {
		this.pictureid = pictureid;
	}
	public java.lang.String getPicName() {
		return this.picName;
	}
	
	public void setPicName(java.lang.String picName) {
		this.picName = picName;
	}
	public java.lang.String getPicUrl() {
		return this.picUrl;
	}
	
	public void setPicUrl(java.lang.String picUrl) {
		this.picUrl = picUrl;
	}
	public java.lang.String getUserId() {
		return this.userId;
	}
	
	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}
	public java.lang.String getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
	

}
