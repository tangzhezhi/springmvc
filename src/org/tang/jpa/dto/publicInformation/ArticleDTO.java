/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.dto.publicInformation;
import java.util.List;

import org.apache.log4j.Logger;

public class ArticleDTO {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	
    private java.lang.String articleId;
    private java.lang.String articleContent;
    private java.lang.String articleType;
    private java.lang.String articleOperater;
    private java.lang.String createTime;
    private java.lang.String articleTitle;
    private List<String> articlePics;
	
	public java.lang.String getArticleId() {
		return this.articleId;
	}
	
	public void setArticleId(java.lang.String articleId) {
		this.articleId = articleId;
	}
	public java.lang.String getArticleContent() {
		return this.articleContent;
	}
	
	public void setArticleContent(java.lang.String articleContent) {
		this.articleContent = articleContent;
	}
	public java.lang.String getArticleType() {
		return this.articleType;
	}
	
	public void setArticleType(java.lang.String articleType) {
		this.articleType = articleType;
	}
	public java.lang.String getArticleOperater() {
		return this.articleOperater;
	}
	
	public void setArticleOperater(java.lang.String articleOperater) {
		this.articleOperater = articleOperater;
	}
	public java.lang.String getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}
	public java.lang.String getArticleTitle() {
		return this.articleTitle;
	}
	
	public void setArticleTitle(java.lang.String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public List<String> getArticlePics() {
		return articlePics;
	}

	public void setArticlePics(List<String> articlePics) {
		this.articlePics = articlePics;
	}
	
}
