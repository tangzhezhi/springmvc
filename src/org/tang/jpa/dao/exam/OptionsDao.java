
package org.tang.jpa.dao.exam;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.exam.OptionsDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface OptionsDao  {
	
    public int insertOptions(OptionsDTO dto);  
    
    public int updateOptions(OptionsDTO dto);  
     
    public int deleteOptions(String optionsId);  
     
    public Page<?> selectOptionsAll(Page<?> page);  
    
}
