/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.publicInformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.publicInformation.EmailDao;
import org.tang.jpa.dto.publicInformation.EmailDTO;
import org.tang.jpa.utils.Page;

@Service
public class EmailService {
	
	@Autowired
	private EmailDao emailDao;
	
	/**
	 * 分页
	 * @param udto
	 * @return
	 */
	public  Page findEmail(Page page){
		Page  pageList = (Page) emailDao.selectEmailAll(page);
		if(pageList!=null && pageList.getResults().size() > 0 ){
			pageList.setPageNo(page.getPageNo());
			pageList.setPageSize(page.getPageSize());
			pageList.setTotalPage(page.getTotalPage());
			pageList.setTotalRecord(page.getTotalRecord());
		}
		return pageList;
	}
	
	public int insertEmail(EmailDTO dto){
		int flag = 0;
		if(dto!=null){
			flag = emailDao.insertEmail(dto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int updateEmail(EmailDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = emailDao.updateEmail(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int deleteEmail(String emailId){
		int flag = 0;
		if(emailId!=null){
			flag = emailDao.deleteEmail(emailId);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
}
