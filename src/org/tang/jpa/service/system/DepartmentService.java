/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.system.DepartmentDao;
import org.tang.jpa.dto.system.DepartmentDTO;
import org.tang.jpa.utils.Page;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentDao departmentDao;
	
	/**
	 * 分页
	 * @param udto
	 * @return
	 */
	public  Page findDepartment(Page page){
		Page  pageList = (Page) departmentDao.selectDepartmentAll(page);
		if(pageList!=null && pageList.getResults().size() > 0 ){
			pageList.setPageNo(page.getPageNo());
			pageList.setPageSize(page.getPageSize());
			pageList.setTotalPage(page.getTotalPage());
			pageList.setTotalRecord(page.getTotalRecord());
		}
		return pageList;
	}
	
	public int insertDepartment(DepartmentDTO dto){
		int flag = 0;
		if(dto!=null){
			flag = departmentDao.insertDepartment(dto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int updateDepartment(DepartmentDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = departmentDao.updateDepartment(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int deleteDepartment(String departmentId){
		int flag = 0;
		if(departmentId!=null){
			flag = departmentDao.deleteDepartment(departmentId);
		}
		else{
			flag = 0;
		}
		return flag;
	}

	public Page queryUpperDepartment(Page page) {
		Page  pageList = (Page) departmentDao.queryUpperDepartment(page);
		if(pageList!=null && pageList.getResults().size() > 0 ){
			pageList.setPageNo(page.getPageNo());
			pageList.setPageSize(page.getPageSize());
			pageList.setTotalPage(page.getTotalPage());
			pageList.setTotalRecord(page.getTotalRecord());
		}
		return pageList;
	}
	
	
	
}
