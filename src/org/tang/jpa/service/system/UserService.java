package org.tang.jpa.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.system.UserDao;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.utils.Page;

@Service("userService")
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * 用户验证--用户登录时
	 * @param dto
	 * @return
	 */
	public UserDTO verifyUserLoginInfo(UserDTO dto) {
		UserDTO udto = null;
		try {
			udto =  userDao.selectUserLoginInfo(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return udto;
	}
	
	

	/**
	 * 分页
	 * @param udto
	 * @return
	 */
	public  Page findUser(Page page){
		Page  pageList = (Page) userDao.selectUserAll(page);
		pageList.setPageNo(page.getPageNo());
		pageList.setPageSize(page.getPageSize());
		pageList.setTotalPage(page.getTotalPage());
		pageList.setTotalRecord(page.getTotalRecord());
		return pageList;
	}
	
	public int insertUser(UserDTO dto){
		int flag = 0;
		if(dto!=null){
			flag = userDao.insertUser(dto);
			flag = userDao.insertUserRole(dto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int updateUser(UserDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = userDao.updateUser(rdto);
			flag = userDao.updateUserRole(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int deleteUser(String userId){
		int flag = 0;
		if(userId!=null){
			flag = userDao.deleteUser(userId);
			flag = userDao.deleteUserRole(userId);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
}
