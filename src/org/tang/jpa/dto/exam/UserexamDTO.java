/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.dto.exam;
import org.apache.log4j.Logger;

public class UserexamDTO {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	
    private java.lang.String userexamid;
    private java.lang.String userid;
    private java.lang.String examid;
    private java.lang.String totalScore;
	
	public java.lang.String getUserexamid() {
		return this.userexamid;
	}
	
	public void setUserexamid(java.lang.String userexamid) {
		this.userexamid = userexamid;
	}
	public java.lang.String getUserid() {
		return this.userid;
	}
	
	public void setUserid(java.lang.String userid) {
		this.userid = userid;
	}
	public java.lang.String getExamid() {
		return this.examid;
	}
	
	public void setExamid(java.lang.String examid) {
		this.examid = examid;
	}
	public java.lang.String getTotalScore() {
		return this.totalScore;
	}
	
	public void setTotalScore(java.lang.String totalScore) {
		this.totalScore = totalScore;
	}

}
