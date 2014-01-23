/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.system.OrganizationDao;
import org.tang.jpa.dto.system.OrganizationDTO;
import org.tang.jpa.utils.Page;

@Service
public class OrganizationService {
	@Autowired
	private OrganizationDao organizationDao;
	
	/**
	 * 分页
	 * @param udto
	 * @return
	 */
	public  Page findOrganization(Page page){
		Page  pageList = (Page) organizationDao.selectOrganizationAll(page);
		pageList.setPageNo(page.getPageNo());
		pageList.setPageSize(page.getPageSize());
		pageList.setTotalPage(page.getTotalPage());
		pageList.setTotalRecord(page.getTotalRecord());
		return pageList;
	}
	
	public int insertOrganization(OrganizationDTO dto){
		int flag = 0;
		if(dto!=null){
			flag = organizationDao.insertOrganization(dto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int updateOrganization(OrganizationDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = organizationDao.updateOrganization(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int deleteOrganization(String organizationId){
		int flag = 0;
		if(organizationId!=null){
			flag = organizationDao.deleteOrganization(organizationId);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
}
