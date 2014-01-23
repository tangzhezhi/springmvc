package org.tang.jpa.dao.system;

import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.system.OperateLogDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface OperateLogDao {
 	
    public int insertOperateLog(OperateLogDTO dto);  
     
    public Page<?> selectOperateLogDTO(Page<?> page);
    
}
