/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.dto.publicInformation;
import org.apache.log4j.Logger;

public class VideoDTO {

	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	
    private java.lang.String videoid;
    private java.lang.String videoName;
    private java.lang.String videoUrl;
    private java.lang.String userid;
    private java.lang.String createtime;
    private java.lang.String clickNum;
    private java.lang.String videoDuration;
    private java.lang.String videoRecommend;
    
    private String videoSubject;
    private String videoTime;
    private String videopic;
	
	public java.lang.String getVideoid() {
		return this.videoid;
	}
	
	public void setVideoid(java.lang.String videoid) {
		this.videoid = videoid;
	}
	public java.lang.String getVideoName() {
		return this.videoName;
	}
	
	public void setVideoName(java.lang.String videoName) {
		this.videoName = videoName;
	}
	public java.lang.String getVideoUrl() {
		return this.videoUrl;
	}
	
	public void setVideoUrl(java.lang.String videoUrl) {
		this.videoUrl = videoUrl;
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
	public java.lang.String getClickNum() {
		return this.clickNum;
	}
	
	public void setClickNum(java.lang.String clickNum) {
		this.clickNum = clickNum;
	}
	public java.lang.String getVideoDuration() {
		return this.videoDuration;
	}
	
	public void setVideoDuration(java.lang.String videoDuration) {
		this.videoDuration = videoDuration;
	}
	public java.lang.String getVideoRecommend() {
		return this.videoRecommend;
	}
	
	public void setVideoRecommend(java.lang.String videoRecommend) {
		this.videoRecommend = videoRecommend;
	}

	public String getVideoSubject() {
		return videoSubject;
	}

	public void setVideoSubject(String videoSubject) {
		this.videoSubject = videoSubject;
	}

	public String getVideoTime() {
		return videoTime;
	}

	public void setVideoTime(String videoTime) {
		this.videoTime = videoTime;
	}

	public String getVideopic() {
		return videopic;
	}

	public void setVideopic(String videopic) {
		this.videopic = videopic;
	}
	
}
