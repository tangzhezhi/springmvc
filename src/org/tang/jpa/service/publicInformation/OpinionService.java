/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.publicInformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.publicInformation.OpinionDao;
import org.tang.jpa.dto.publicInformation.OpinionDTO;
import org.tang.jpa.utils.Page;

@Service
public class OpinionService {
	@Autowired
	private OpinionDao opinionDao;
	
	/**
	 * 分页
	 * @param udto
	 * @return
	 */
	public  Page findOpinion(Page page){
		Page  pageList = (Page) opinionDao.selectOpinionAll(page);
		if(pageList!=null && pageList.getResults().size() > 0 ){
			pageList.setPageNo(page.getPageNo());
			pageList.setPageSize(page.getPageSize());
			pageList.setTotalPage(page.getTotalPage());
			pageList.setTotalRecord(page.getTotalRecord());
		}
		return pageList;
	}
	
	public int insertOpinion(OpinionDTO dto){
		int flag = 0;
		if(dto!=null){
			flag = opinionDao.insertOpinion(dto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int updateOpinion(OpinionDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = opinionDao.updateOpinion(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	public int updateApproveOpinion(OpinionDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = opinionDao.updateApproveOpinion(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	
	
	public int deleteOpinion(String opinionId){
		int flag = 0;
		if(opinionId!=null){
			flag = opinionDao.deleteOpinion(opinionId);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	public OpinionDTO selectOpinion(String opinionId){
		return opinionDao.selectOpinion(opinionId);
	}
	
	
	
	
}
