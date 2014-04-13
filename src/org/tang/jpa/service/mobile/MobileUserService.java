package org.tang.jpa.service.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.mobile.MobileUserDao;
import org.tang.jpa.dto.mobile.MobileUserDTO;

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
}
