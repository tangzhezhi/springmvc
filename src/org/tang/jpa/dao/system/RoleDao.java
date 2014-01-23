
package org.tang.jpa.dao.system;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.system.RoleDTO;
import org.tang.jpa.dto.system.TreeDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface RoleDao  {
	
    public int insertRole(RoleDTO dto);  
    
    public int insertRoleRole(RoleDTO dto);  
    
    public int updateRole(RoleDTO dto);  
     
    public int deleteRole(String roleId);  
     
    public Page<?> selectRoleAll(Page<?> page);
    
	public List<TreeDTO> findRoleAuthTree(UserDTO dto);

	public int saveRoleAuth(List l);

	public int deleteRoleAuth(String roleid);  
    
}
