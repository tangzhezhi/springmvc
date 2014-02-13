
package org.tang.jpa.dao.system;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.system.DepartmentDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface DepartmentDao  {
	
    public int insertDepartment(DepartmentDTO dto);  
    
    public int insertRoleDepartment(DepartmentDTO dto);  
    
    public int updateDepartment(DepartmentDTO dto);  
     
    public int deleteDepartment(String departmentId);  
     
    public Page<?> selectDepartmentAll(Page<?> page);

	public Page queryUpperDepartment(Page page);  
    
}
