package org.tang.jpa.controller.attendance;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.tang.jpa.dto.attendance.AttendanceStateDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.attendance.AttendanceStateService;
import org.tang.jpa.utils.MyConstants;
import org.tang.jpa.utils.Page;

@Controller("attendanceStateController")  
@RequestMapping("attendanceState")  
@SessionAttributes("currentUser")
public class AttendanceStateController {
	@Autowired
	private AttendanceStateService attendanceStateService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryAttendanceState", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryAttendanceState(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="attendanceStatename",required=false) String attendanceStateName
    		,@RequestParam(value="page",required=false) int pageNo
    		,@RequestParam(value="rows",required=false) int pageSize) {  
        Map<String, Object> model = new HashMap<String, Object>();
        
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map params = new HashMap();
        params.put("attendanceStatename", attendanceStateName);
        page.setParams(params);
        Page p = attendanceStateService.findAttendanceState(page);
        model.put("rows",p==null?0:p.getResults());
        model.put("total", p==null?0:p.getTotalRecord());
        return model;  
    }  
	
	
	@RequestMapping(value = "/addAttendanceState", method = RequestMethod.POST)  
    @ResponseBody  
    public String addAttendanceState(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="id",required=false) String id,
				@RequestParam(value="userId",required=false) String userId,
				@RequestParam(value="state",required=false) String state,
				@RequestParam(value="attendanceDate",required=false) String attendanceDate
    		) {  
	        AttendanceStateDTO rdto = new AttendanceStateDTO();
	        	rdto.setId(id);
	        	rdto.setUserId(userId);
	        	rdto.setState(state);
	        	rdto.setAttendanceDate(attendanceDate);
	       
	        int flag =  attendanceStateService.insertAttendanceState(rdto);
	        if(flag == 1){
	        	return MyConstants.ADDSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.ADDFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/modifyAttendanceState", method = RequestMethod.POST)  
    @ResponseBody  
    public String modifyAttendanceState(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="id",required=false) String id,
				@RequestParam(value="userId",required=false) String userId,
				@RequestParam(value="state",required=false) String state,
				@RequestParam(value="attendanceDate",required=false) String attendanceDate
    		) {  
	        AttendanceStateDTO rdto = new AttendanceStateDTO();
        		rdto.setId(id);
        		rdto.setUserId(userId);
        		rdto.setState(state);
        		rdto.setAttendanceDate(attendanceDate);
	       
	        int flag =  attendanceStateService.updateAttendanceState(rdto);
	        if(flag == 1){
	        	return MyConstants.MODIFYSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.MODIFYFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/deleteAttendanceState", method = RequestMethod.POST)  
    @ResponseBody  
    public String deleteAttendanceState(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="attendanceStateid",required=true) String attendanceStateId) {  
			AttendanceStateDTO rdto = new AttendanceStateDTO();
	        int flag =  attendanceStateService.deleteAttendanceState(attendanceStateId);
	        if(flag == 1){
	        	return MyConstants.DELSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.DELFAIL.getName();
	        }
    }
	
}
