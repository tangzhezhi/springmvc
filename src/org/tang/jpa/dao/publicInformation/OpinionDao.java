
package org.tang.jpa.dao.publicInformation;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.publicInformation.OpinionDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface OpinionDao  {
	
    public int insertOpinion(OpinionDTO dto);  
    
    public int insertRoleOpinion(OpinionDTO dto);  
    
    public int updateOpinion(OpinionDTO dto);  
     
    public int deleteOpinion(String opinionId);  
     
    public Page<?> selectOpinionAll(Page<?> page);  
    
    public OpinionDTO selectOpinion(String opinionid);

	public int updateApproveOpinion(OpinionDTO rdto);
    
}
