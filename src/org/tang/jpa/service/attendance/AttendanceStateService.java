/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.attendance;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.attendance.AttendanceStateDao;
import org.tang.jpa.dto.attendance.AttendanceState;
import org.tang.jpa.dto.attendance.AttendanceStateDTO;
import org.tang.jpa.dto.attendance.AttendanceStateDetailDTO;
import org.tang.jpa.utils.Page;
import org.tang.jpa.utils.SpecialCalendar;

@Service
public class AttendanceStateService {
	@Autowired
	private AttendanceStateDao attendanceStateDao;
	
	/**
	 * 分页
	 * @param udto
	 * @return
	 */
	public  Page findAttendanceState(Page page){
		Page  pageList = (Page) attendanceStateDao.selectAttendanceStateAll(page);
		if(pageList!=null && pageList.getResults().size() > 0 ){
			pageList.setPageNo(page.getPageNo());
			pageList.setPageSize(page.getPageSize());
			pageList.setTotalPage(page.getTotalPage());
			pageList.setTotalRecord(page.getTotalRecord());
		}
		return pageList;
	}
	
	public Map<String,List<AttendanceState>>  selectAttendanceStateDetailByMonth(String userId,String year,String month){
		SpecialCalendar specialCalendar = new SpecialCalendar();
		ArrayList<String> al = specialCalendar.getDaysOfMonth(Integer.valueOf(year),Integer.valueOf(month));
		
		HashMap<String,List<AttendanceState>> m = new 	HashMap<String,List<AttendanceState>>();
		
		for(String date : al){
			AttendanceStateDTO at = new AttendanceStateDTO();
			at.setUserId(userId);
			at.setAttendanceDate(date);
			List<AttendanceState> ldto = attendanceStateDao.selectAttendanceState(at);
			
//			if(null==ldto || ldto.size() == 0){
//				ldto = new ArrayList<AttendanceState>();
//				AttendanceState ds = new AttendanceState();
//				ds.setId(UUID.randomUUID().toString());
//				ds.setAttendanceName("无数据");
//				ds.setAttendanceTime("无打卡时间");
//				ds.setState("无状态");
//				ds.setUserId(userId);
//				ldto.add(ds);
//			}
			
			m.put(date, ldto);
		}
		return m;
	}
	
	
	
	
	public int insertAttendanceState(AttendanceStateDTO dto){
		int flag = 0;
		if(dto!=null){
			flag = attendanceStateDao.insertAttendanceState(dto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int updateAttendanceState(AttendanceStateDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = attendanceStateDao.updateAttendanceState(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int deleteAttendanceState(String attendanceStateId){
		int flag = 0;
		if(attendanceStateId!=null){
			flag = attendanceStateDao.deleteAttendanceState(attendanceStateId);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
}
