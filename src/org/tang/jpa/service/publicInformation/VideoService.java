/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.publicInformation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.publicInformation.VideoDao;
import org.tang.jpa.dto.publicInformation.VideoDTO;
import org.tang.jpa.utils.Page;

@Service
public class VideoService {
	@Autowired
	private VideoDao videoDao;
	
	/**
	 * 分页
	 * @param udto
	 * @return
	 */
	public  Page findVideo(Page page){
		Page  pageList = (Page) videoDao.selectVideoAll(page);
		if(pageList!=null && pageList.getResults().size() > 0 ){
			pageList.setPageNo(page.getPageNo());
			pageList.setPageSize(page.getPageSize());
			pageList.setTotalPage(page.getTotalPage());
			pageList.setTotalRecord(page.getTotalRecord());
		}
		return pageList;
	}
	
	public int insertVideo(VideoDTO dto){
		int flag = 0;
		if(dto!=null){
			flag = videoDao.insertVideo(dto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int updateVideo(VideoDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = videoDao.updateVideo(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int deleteVideo(String videoId){
		int flag = 0;
		if(videoId!=null){
			flag = videoDao.deleteVideo(videoId);
		}
		else{
			flag = 0;
		}
		return flag;
	}

	public List findVideoTree() throws Exception {
		List result = null;
		try {
			result =  videoDao.findVideoTree();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
		
	}
	
	
	
}
