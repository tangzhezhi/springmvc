/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.system.RoleDao;
import org.tang.jpa.dto.system.RoleDTO;
import org.tang.jpa.dto.system.RoleResourceDTO;
import org.tang.jpa.dto.system.TreeDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.utils.Page;

@Service
public class RoleService {
	@Autowired
	private RoleDao roleDao;
	
	/**
	 * 分页
	 * @param udto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  Page findRole(Page page){
		Page  pageList = (Page) roleDao.selectRoleAll(page);
		pageList.setPageNo(page.getPageNo());
		pageList.setPageSize(page.getPageSize());
		pageList.setTotalPage(page.getTotalPage());
		pageList.setTotalRecord(page.getTotalRecord());
		return pageList;
	}
	
	public int insertRole(RoleDTO dto){
		int flag = 0;
		if(dto!=null){
			flag = roleDao.insertRole(dto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int updateRole(RoleDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = roleDao.updateRole(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int deleteRole(String roleId){
		int flag = 0;
		if(roleId!=null){
			flag = roleDao.deleteRole(roleId);
		}
		else{
			flag = 0;
		}
		return flag;
	}

	public List<TreeDTO> findRoleAuthTree(UserDTO dto) {
		return roleDao.findRoleAuthTree(dto);
	}
	
	
	public int saveRoleAuth(String currentRoleid,String roleid, String resources) {
		int flag = 0;
		List l = null;
		if(resources!=null && resources.length() > 0){
			l = new ArrayList();
			flag = roleDao.deleteRoleAuth(roleid);
			String[] resourcearr = resources.split(",");
			for(String resource : resourcearr){
				RoleResourceDTO rr = new RoleResourceDTO();
				rr.setCurrentroleid(currentRoleid);
				rr.setRoleid(roleid);
				rr.setResourceid(resource);
				l.add(rr);
			}
			flag = roleDao.saveRoleAuth(l);
		}
		return flag ;
	}
	
	
	
}
