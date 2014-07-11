package org.tang.jpa.controller.mobile;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tang.jpa.dto.attendance.AttendanceState;
import org.tang.jpa.service.attendance.AttendanceStateService;
import org.tang.jpa.utils.MobileConstant;

@Controller("attendanceStateMobileController")  
@RequestMapping("mobile")  
public class AttendanceStateController {
	@Autowired
	private AttendanceStateService attendanceStateService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryAttendanceState", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public ModelMap queryAttendanceState(
    		@RequestParam(value="userId",required=false) String userId,
    		@RequestParam(value="year",required=false) String year,
    		@RequestParam(value="month",required=false) String month) {  
		ModelMap mm = new ModelMap();
        
		Map<String,List<AttendanceState>>  m = attendanceStateService.selectAttendanceStateDetailByMonth(userId,year,month);
        
        if(m!=null){
        	mm.put("sessionKey", "examTang");
        	mm.put("msgFlag", MobileConstant.attendance_query_state_success);
        	mm.put("response", m);
        }
        else{
        	mm.put("msgFlag", MobileConstant.attendance_query_state_fail);
        }
		
        return mm;  
    }  
	
	
}
