package org.tang.jpa.service.mobile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.mobile.MobileUserDao;
import org.tang.jpa.dto.mobile.AttendanceGraphDTO;
import org.tang.jpa.dto.mobile.MobileUserDTO;
import org.tang.jpa.dto.mobile.UserInfoDTO;

@Service("mobileUserService")
public class MobileUserService {
	
	@Autowired
	private MobileUserDao mobileUserDao;
	
	/**
	 * 用户验证--用户登录时
	 * @param dto
	 * @return
	 */
	public MobileUserDTO verifyMobileUserLoginInfo(MobileUserDTO dto) {
		MobileUserDTO udto = null;
		try {
			udto =  mobileUserDao.selectMobileUserLoginInfo(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return udto;
	}

	public List<UserInfoDTO> queryContactUserInfo(MobileUserDTO udto) {
		
		List<UserInfoDTO> udtoList = null;
		try {
			udtoList =  mobileUserDao.queryContactUserInfo(udto);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return udtoList;
	}

	
	public int addPushInfo(UserInfoDTO udto){
		int flag = 0;
		if(udto!=null){
			flag = mobileUserDao.addPushInfo(udto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
}
