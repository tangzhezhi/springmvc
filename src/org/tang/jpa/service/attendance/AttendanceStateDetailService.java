/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.attendance;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.attendance.AttendanceStateDetailDao;
import org.tang.jpa.dto.attendance.AttendanceStateDetailDTO;
import org.tang.jpa.utils.Page;

@Service
public class AttendanceStateDetailService {
	@Autowired
	private AttendanceStateDetailDao attendanceStateDetailDao;
	
	/**
	 * 分页
	 * @param udto
	 * @return
	 */
	public  Page findAttendanceStateDetail(Page page){
		Page  pageList = (Page) attendanceStateDetailDao.selectAttendanceStateDetailAll(page);
		if(pageList!=null && pageList.getResults().size() > 0 ){
			pageList.setPageNo(page.getPageNo());
			pageList.setPageSize(page.getPageSize());
			pageList.setTotalPage(page.getTotalPage());
			pageList.setTotalRecord(page.getTotalRecord());
		}
		return pageList;
	}
	
	public List<AttendanceStateDetailDTO>  selectAttendanceStateDetail(AttendanceStateDetailDTO dto){
		return attendanceStateDetailDao.selectAttendanceStateDetail(dto);
	}
	
	
	public int insertAttendanceStateDetail(AttendanceStateDetailDTO dto){
		int flag = 0;
		if(dto!=null){
			flag = attendanceStateDetailDao.insertAttendanceStateDetail(dto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int updateAttendanceStateDetail(AttendanceStateDetailDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = attendanceStateDetailDao.updateAttendanceStateDetail(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int deleteAttendanceStateDetail(String attendanceStateDetailId){
		int flag = 0;
		if(attendanceStateDetailId!=null){
			flag = attendanceStateDetailDao.deleteAttendanceStateDetail(attendanceStateDetailId);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
}
