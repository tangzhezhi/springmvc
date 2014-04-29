package org.tang.jpa.controller.mobile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tang.jpa.dto.mobile.AttendanceDTO;
import org.tang.jpa.dto.mobile.MobileBaseRepDTO;
import org.tang.jpa.service.mobile.AttendanceService;
import org.tang.jpa.utils.DateTool;
import org.tang.jpa.utils.MobileConstant;
import org.tang.jpa.utils.MyConstants;
import org.tang.jpa.utils.Page;

import com.google.gson.Gson;

@Controller("attendanceController")  
@RequestMapping("mobile")  
public class AttendanceController {
	@Autowired
	private AttendanceService attendanceService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryAttendance", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public String queryAttendance(
    		@RequestParam(value="userId",required=false) String UserId,
    		@RequestParam(value="createTime",required=false) String createTime
    		) {  
		AttendanceDTO dto = new AttendanceDTO();
		dto.setUserId(UserId);
		String result="";
        if(StringUtils.isEmpty(createTime)){
        	dto.setCreateTime(DateTool.getDateStringYMDHMS(new Date()));		
        }
        else{
        	dto.setCreateTime(createTime);		
        }
        List<AttendanceDTO> list = attendanceService.findAttendance(dto);
        
        MobileBaseRepDTO mbt = new MobileBaseRepDTO();
        Gson gson = new Gson();  
        
        if(list!=null){
        	mbt.setSessionKey("examTang");
        	mbt.setMsgFlag(MobileConstant.attendance_success);
        	mbt.setResponse(gson.toJson(list));
        }
        else{
        	mbt.setMsgFlag(MobileConstant.attendance_fail);
        }
        result = gson.toJson(mbt);  
        return result;  
    }  
	
	
	@RequestMapping(value = "/addAttendance", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public String addAttendance(
				@RequestParam(value="userId",required=false) String UserId,
				@RequestParam(value="userName",required=false) String UserName,
				@RequestParam(value="createTime",required=false) String CreateTime,
				@RequestParam(value="gps",required=false) String Gps,
				@RequestParam(value="address",required=false) String Address
    		) {  
			String result="";
	        	AttendanceDTO rdto = new AttendanceDTO();
	        	rdto.setId(UUID.randomUUID().toString());
	        	rdto.setUserId(UserId);
	        	rdto.setUserName(UserName);
	        	rdto.setCreateTime(CreateTime);
	        	rdto.setGps(Gps);
	        	rdto.setAddress(Address);
	       
	        int flag =  attendanceService.insertAttendance(rdto);
	        
	        MobileBaseRepDTO mbt = new MobileBaseRepDTO();
	        Gson gson = new Gson();  
	    	mbt.setSessionKey("examTang");
        	mbt.setMsgFlag(MobileConstant.attendance_success);
	        if(flag == 1){
	        	mbt.setMsgFlag(MobileConstant.attendance_upload_success);
	        }
	        else{
	        	mbt.setMsgFlag(MobileConstant.attendance_upload_fail);
	        }
	        
	        result = gson.toJson(mbt);  
	        return result;  
    }
	
	
	
	@RequestMapping(value = "/modifyAttendance", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public String modifyAttendance(
				@RequestParam(value="userId",required=false) String UserId,
				@RequestParam(value="userName",required=false) String UserName,
				@RequestParam(value="createTime",required=false) String CreateTime,
				@RequestParam(value="gps",required=false) String Gps,
				@RequestParam(value="address",required=false) String Address
    		) {  
	        	AttendanceDTO rdto = new AttendanceDTO();
        		rdto.setUserId(UserId);
        		rdto.setUserName(UserName);
        		rdto.setCreateTime(CreateTime);
        		rdto.setGps(Gps);
        		rdto.setAddress(Address);
	        int flag =  attendanceService.updateAttendance(rdto);
	        if(flag == 1){
	        	return "SUCCESS";
	        }
	        else{
	        	return "FAILED";
	        }
    }
	
	
	
	@RequestMapping(value = "/deleteAttendance", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public String deleteAttendance(@RequestParam(value="userId",required=true) String userId,
    		@RequestParam(value="createTime",required=false) String CreateTime) {  
			AttendanceDTO rdto = new AttendanceDTO();
			rdto.setUserId(userId);
			rdto.setCreateTime(CreateTime);
	        int flag =  attendanceService.deleteAttendance(rdto);
	        if(flag == 1){
	        	return "SUCCESS";
	        }
	        else{
	        	return "FAILED";
	        }
    }
	
}
