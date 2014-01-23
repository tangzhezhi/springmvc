
package org.tang.jpa.dao.exam;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.exam.UserexamdetailsDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface UserexamdetailsDao  {
	
    public int insertUserexamdetails(UserexamdetailsDTO dto);  
    
    public int insertRoleUserexamdetails(UserexamdetailsDTO dto);  
    
    public int updateUserexamdetails(UserexamdetailsDTO dto);  
     
    public int deleteUserexamdetails(String userexamdetailsId);  
     
    public Page<?> selectUserexamdetailsAll(Page<?> page);  
    
}
