
package org.tang.jpa.dao.mobile;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.mobile.TaskWeeklyDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface TaskWeeklyDao  {
	
    public int insertTaskWeekly(TaskWeeklyDTO dto);  
    
    public int insertRoleTaskWeekly(TaskWeeklyDTO dto);  
    
    public int updateTaskWeekly(TaskWeeklyDTO dto);  
     
    public int deleteTaskWeekly(String noticeId);  
     
    public Page<?> selectTaskWeeklyAll(Page<?> page);  
    
	public List<TaskWeeklyDTO> queryTaskWeekly(TaskWeeklyDTO udto);  
    
}
