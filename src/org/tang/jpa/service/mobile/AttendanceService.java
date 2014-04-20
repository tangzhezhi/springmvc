/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.mobile;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.mobile.AttendanceDao;
import org.tang.jpa.dto.mobile.AttendanceDTO;

@Service
public class AttendanceService {
	@Autowired
	private AttendanceDao attendanceDao;
	
	public  List<AttendanceDTO> findAttendance(AttendanceDTO dto){
		List<AttendanceDTO> list =  attendanceDao.selectAttendance(dto);
		return list;
	}
	
	public int insertAttendance(AttendanceDTO dto){
		int flag = 0;
		if(dto!=null){
			flag = attendanceDao.insertAttendance(dto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int updateAttendance(AttendanceDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = attendanceDao.updateAttendance(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int deleteAttendance(AttendanceDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = attendanceDao.deleteAttendance(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
}
