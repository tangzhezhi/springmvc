package org.tang.jpa.dao.system;

import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.system.LoginLogDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface LoginLogDao {
 	
    public int insertLoginLog(LoginLogDTO dto);  
     
    public Page<?> selectLoginLogDTO(Page<?> page);
    
}
