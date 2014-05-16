
package org.tang.jpa.dao.mobile;

import java.util.List;
import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.mobile.AttendanceGraphDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface AttendanceGraphDao  {
	
    public int insertAttendance(AttendanceGraphDTO dto);  
    
    public int insertRoleAttendance(AttendanceGraphDTO dto);  
    
    public int updateAttendance(AttendanceGraphDTO dto);  
     
    public int deleteAttendance(AttendanceGraphDTO dto);  
     
    public Page<?> selectAttendanceAll(Page<?> page);

	public List<AttendanceGraphDTO> selectAttendance(AttendanceGraphDTO dto);  
	
    
}
