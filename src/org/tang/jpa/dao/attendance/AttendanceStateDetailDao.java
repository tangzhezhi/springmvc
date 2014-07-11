
package org.tang.jpa.dao.attendance;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.attendance.AttendanceStateDetailDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface AttendanceStateDetailDao  {
	
    public int insertAttendanceStateDetail(AttendanceStateDetailDTO dto);  
    
    public int insertRoleAttendanceStateDetail(AttendanceStateDetailDTO dto);  
    
    public int updateAttendanceStateDetail(AttendanceStateDetailDTO dto);  
     
    public int deleteAttendanceStateDetail(String id);  
     
    public Page<?> selectAttendanceStateDetailAll(Page<?> page);  
    
    public List<AttendanceStateDetailDTO> selectAttendanceStateDetail(AttendanceStateDetailDTO dto);
}
