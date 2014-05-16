/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.mobile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.mobile.AttendanceGraphDao;
import org.tang.jpa.dto.mobile.AttendanceGraphDTO;

@Service
public class AttendanceGraphService {
	@Autowired
	private AttendanceGraphDao AttendanceGraphDao;
	
	public  List<AttendanceGraphDTO> findAttendanceGraph(AttendanceGraphDTO dto){
		List<AttendanceGraphDTO> list =  AttendanceGraphDao.selectAttendance(dto);
		return list;
	}
	
	public int insertAttendanceGraph(AttendanceGraphDTO dto){
		int flag = 0;
		if(dto!=null){
			flag = AttendanceGraphDao.insertAttendance(dto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int updateAttendanceGraph(AttendanceGraphDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = AttendanceGraphDao.updateAttendance(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int deleteAttendanceGraph(AttendanceGraphDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = AttendanceGraphDao.deleteAttendance(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
}
