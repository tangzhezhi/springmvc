package org.tang.jpa.dao.mobile;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.mobile.MobileUserDTO;
import org.tang.jpa.dto.mobile.UserInfoDTO;

@Repository
public interface MobileUserDao {
    
	public MobileUserDTO selectMobileUserLoginInfo(MobileUserDTO dto);

	public List<UserInfoDTO> queryContactUserInfo(MobileUserDTO udto);

	public int addPushInfo(UserInfoDTO udto);  
}
