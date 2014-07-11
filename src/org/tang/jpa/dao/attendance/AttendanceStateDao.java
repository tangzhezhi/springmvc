
package org.tang.jpa.dao.attendance;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.attendance.AttendanceState;
import org.tang.jpa.dto.attendance.AttendanceStateDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface AttendanceStateDao  {
	
    public int insertAttendanceState(AttendanceStateDTO dto);  
    
    public int insertRoleAttendanceState(AttendanceStateDTO dto);  
    
    public int updateAttendanceState(AttendanceStateDTO dto);  
     
    public int deleteAttendanceState(String id);  
     
    public Page<?> selectAttendanceStateAll(Page<?> page);  
    
    public List<AttendanceState> selectAttendanceState(AttendanceStateDTO dto);
}
