package org.tang.jpa.dao.system;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.system.ResourceDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface ResourceDao {
 	
    public int insertResource(ResourceDTO dto);  
    
    public int insertRoleResource(ResourceDTO dto);  
    
    public int updateResource(ResourceDTO dto);  
     
    public int deleteResource(String resourceid);  
     
    public Page<?> selectResourceAll(Page<?> page);  
    
    public List<ResourceDTO> selectResourceOfUser(ResourceDTO dto);

}
