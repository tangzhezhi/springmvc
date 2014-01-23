package org.tang.jpa.dao.system;

import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface UserDao {
 	
    public int insertUser(UserDTO dto);  
    
    public int insertUserRole(UserDTO dto);  
     
    public int updateUser(UserDTO dto);  
    
    public int updateUserRole(UserDTO dto);  
     
    public int deleteUser(String userId);  
    
    public int deleteUserRole(String userId);  
     
    public Page<?> selectUserAll(Page<?> page);
    
	public UserDTO selectUserLoginInfo(UserDTO dto);  
}
