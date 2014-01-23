/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.dto.exam;
import org.apache.log4j.Logger;

public class UserexamdetailsDTO {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	
    private java.lang.String userexamid;
    private java.lang.String optionid;
    private java.lang.String userAnswer;
    private java.lang.String optionEarnScore;
    private java.lang.String userexamdetailsid;
	
	public java.lang.String getUserexamid() {
		return this.userexamid;
	}
	
	public void setUserexamid(java.lang.String userexamid) {
		this.userexamid = userexamid;
	}
	public java.lang.String getOptionid() {
		return this.optionid;
	}
	
	public void setOptionid(java.lang.String optionid) {
		this.optionid = optionid;
	}
	public java.lang.String getUserAnswer() {
		return this.userAnswer;
	}
	
	public void setUserAnswer(java.lang.String userAnswer) {
		this.userAnswer = userAnswer;
	}
	public java.lang.String getOptionEarnScore() {
		return this.optionEarnScore;
	}
	
	public void setOptionEarnScore(java.lang.String optionEarnScore) {
		this.optionEarnScore = optionEarnScore;
	}
	public java.lang.String getUserexamdetailsid() {
		return this.userexamdetailsid;
	}
	
	public void setUserexamdetailsid(java.lang.String userexamdetailsid) {
		this.userexamdetailsid = userexamdetailsid;
	}

}
