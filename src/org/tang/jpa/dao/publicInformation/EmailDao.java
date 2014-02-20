
package org.tang.jpa.dao.publicInformation;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.publicInformation.EmailDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface EmailDao  {
	
    public int insertEmail(EmailDTO dto);  
    
    public int insertRoleEmail(EmailDTO dto);  
    
    public int updateEmail(EmailDTO dto);  
     
    public int deleteEmail(String emailId);  
     
    public Page<?> selectEmailAll(Page<?> page);  
    
    public List<EmailDTO> selectEmailAllUnSend(EmailDTO dto);
    
}
