
package org.tang.jpa.dao.exam;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.exam.UserexamDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface UserexamDao  {
	
    public int insertUserexam(UserexamDTO dto);  
    
    public int insertRoleUserexam(UserexamDTO dto);  
    
    public int updateUserexam(UserexamDTO dto);  
     
    public int deleteUserexam(String userexamId);  
     
    public Page<?> selectUserexamAll(Page<?> page);  
    
}
