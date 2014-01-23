/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.exam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.exam.OptionsDao;
import org.tang.jpa.dto.exam.OptionsDTO;
import org.tang.jpa.utils.Page;

@Service
public class OptionsService {
	@Autowired
	private OptionsDao optionsDao;
	
	/**
	 * 分页
	 * @param udto
	 * @return
	 */
	public  Page findOptions(Page page){
		Page  pageList = (Page) optionsDao.selectOptionsAll(page);
		if(pageList!=null && pageList.getResults().size() > 0 ){
			pageList.setPageNo(page.getPageNo());
			pageList.setPageSize(page.getPageSize());
			pageList.setTotalPage(page.getTotalPage());
			pageList.setTotalRecord(page.getTotalRecord());
		}
		return pageList;
	}
	
	public int insertOptions(OptionsDTO dto){
		int flag = 0;
		if(dto!=null){
			flag = optionsDao.insertOptions(dto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int updateOptions(OptionsDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = optionsDao.updateOptions(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int deleteOptions(String optionsId){
		int flag = 0;
		if(optionsId!=null){
			flag = optionsDao.deleteOptions(optionsId);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
}
