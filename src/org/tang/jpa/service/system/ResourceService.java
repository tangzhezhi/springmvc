package org.tang.jpa.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.system.ResourceDao;
import org.tang.jpa.dto.system.ResourceDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.utils.Page;

@Service("resourceService")
public class ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;
	
	/**
	 * 返回用户菜单资源
	 * @param udto
	 * @return
	 */
	public  List<ResourceDTO> findUserResource(ResourceDTO udto){
		List<ResourceDTO> resourceDtoList = resourceDao.selectResourceOfUser(udto);
		return resourceDtoList;
	}
	
	/**
	 * 查询资源--分页
	 * @param udto
	 * @return
	 */
	public  Page findResource(Page page){
		Page  pageList = (Page) resourceDao.selectResourceAll(page);
		pageList.setPageNo(page.getPageNo());
		pageList.setPageSize(page.getPageSize());
		pageList.setTotalPage(page.getTotalPage());
		pageList.setTotalRecord(page.getTotalRecord());
		return pageList;
	}
	
	public int insertResource(ResourceDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = resourceDao.insertResource(rdto);
			flag = resourceDao.insertRoleResource(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int updateResource(ResourceDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = resourceDao.updateResource(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int deleteResource(String  resourceid){
		int flag = 0;
		if(resourceid!=null){
			flag = resourceDao.deleteResource(resourceid);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
}
