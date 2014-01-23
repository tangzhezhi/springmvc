
package org.tang.jpa.dao.system;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.system.OrganizationDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface OrganizationDao  {
	
    public int insertOrganization(OrganizationDTO dto);  
    
    public int insertRoleOrganization(OrganizationDTO dto);  
    
    public int updateOrganization(OrganizationDTO dto);  
     
    public int deleteOrganization(String organizationId);  
     
    public Page<?> selectOrganizationAll(Page<?> page);  
    
}
