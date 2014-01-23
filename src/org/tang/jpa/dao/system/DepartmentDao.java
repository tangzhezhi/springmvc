package org.tang.jpa.dao.system;

import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.system.DepartmentDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface DepartmentDao {
 	
    public int insertDepartment(DepartmentDTO dto);  
     
    public int updateDepartment(DepartmentDTO dto);  
     
    public int deleteDepartment(String DepartmentId);  
     
    public Page<?> selectDepartmentAll(Page<?> page);  
}
