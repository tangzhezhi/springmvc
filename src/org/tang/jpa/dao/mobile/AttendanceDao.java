
package org.tang.jpa.dao.mobile;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.mobile.AttendanceDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface AttendanceDao  {
	
    public int insertAttendance(AttendanceDTO dto);  
    
    public int insertRoleAttendance(AttendanceDTO dto);  
    
    public int updateAttendance(AttendanceDTO dto);  
     
    public int deleteAttendance(AttendanceDTO dto);  
     
    public Page<?> selectAttendanceAll(Page<?> page);

	public List<AttendanceDTO> selectAttendance(AttendanceDTO dto);  
	
    
}
