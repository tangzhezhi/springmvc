package org.tang.jpa.dao.mobile;

import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.mobile.MobileUserDTO;

@Repository
public interface MobileUserDao {
    
	public MobileUserDTO selectMobileUserLoginInfo(MobileUserDTO dto);  
}
