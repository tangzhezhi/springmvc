package org.tang.jpa.controller.mobile;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.tang.jpa.dto.mobile.AttendanceGraphDTO;
import org.tang.jpa.dto.mobile.MobileBaseRepDTO;
import org.tang.jpa.service.mobile.AttendanceGraphService;
import org.tang.jpa.utils.DateTool;
import org.tang.jpa.utils.MobileConstant;

import com.google.gson.Gson;

@Controller("attendanceGraphController")  
@RequestMapping("mobile")  
public class AttendanceGraphController {
	
	@Autowired  
	private  HttpServletRequest request; 
	
	@Autowired
	private AttendanceGraphService attendanceService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryAttendanceGraph", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public String queryAttendance(
    		@RequestParam(value="userId",required=false) String UserId,
    		@RequestParam(value="createTime",required=false) String createTime
    		) {  
		AttendanceGraphDTO dto = new AttendanceGraphDTO();
		dto.setUserId(UserId);
		String result="";
        if(StringUtils.isEmpty(createTime)){
        	dto.setCreateTime(DateTool.getDateStringYMDHMS(new Date()));		
        }
        else{
        	dto.setCreateTime(createTime);		
        }
        List<AttendanceGraphDTO> list = attendanceService.findAttendanceGraph(dto);
        
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
	
	
	@RequestMapping(value = "/addAttendanceGraph", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public ModelMap addAttendance(
				@RequestParam(value="userId",required=false) String UserId,
				@RequestParam(value="userName",required=false) String UserName,
				@RequestParam(value="createTime",required=false) String CreateTime,
				@RequestParam(value="gps",required=false) String Gps,
				@RequestParam(value="address",required=false) String Address,
				@RequestParam(value="latitude",required=false) String latitude,
				@RequestParam(value="longitude",required=false) String longitude,
				@RequestParam(value="photo",required=false) MultipartFile file
    		) {  
				ModelMap mm = new ModelMap();
		        mm.put("sessionKey", "examTang");
	        	AttendanceGraphDTO rdto = new AttendanceGraphDTO();
	        	rdto.setId(UUID.randomUUID().toString());
	        	rdto.setUserId(UserId);
	        	rdto.setUserName(UserName);
	        	rdto.setCreateTime(CreateTime);
	        	rdto.setGps(Gps);
	        	rdto.setAddress(Address);
	        	rdto.setLatitude(latitude);
	        	rdto.setLongitude(longitude);
	        	
	        	 String path =  "/resources/upload_pic/attendances";
	        	 String filePath = request.getSession().getServletContext().getRealPath(path);
	        	  
	        	 String fileName = file.getOriginalFilename();//上传的文件名字
        	     File targetFile = new File(filePath, fileName);
        	    if (!targetFile.exists()) {
        	        targetFile.mkdirs();
        	    }
        	    // 保存
        	    try {
        	        file.transferTo(targetFile);
        	        rdto.setPhotoUrl(targetFile.getAbsolutePath());
        	    } catch (Exception e) {
        	        e.printStackTrace();
                	mm.put("msgFlag", MobileConstant.attendance_upload_fail);
        	        return mm;
        	    }
	        	
	        	
	        int flag =  attendanceService.insertAttendanceGraph(rdto);
	        

	        if(flag == 1){
	        	mm.put("msgFlag", MobileConstant.attendance_upload_success);
	        }
	        else{
	        	mm.put("msgFlag", MobileConstant.attendance_upload_fail);
	        }
	        return mm;  
    }
	
	
	
	@RequestMapping(value = "/modifyAttendanceGraph", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public String modifyAttendance(
				@RequestParam(value="userId",required=false) String UserId,
				@RequestParam(value="userName",required=false) String UserName,
				@RequestParam(value="createTime",required=false) String CreateTime,
				@RequestParam(value="gps",required=false) String Gps,
				@RequestParam(value="address",required=false) String Address
    		) {  
	        	AttendanceGraphDTO rdto = new AttendanceGraphDTO();
        		rdto.setUserId(UserId);
        		rdto.setUserName(UserName);
        		rdto.setCreateTime(CreateTime);
        		rdto.setGps(Gps);
        		rdto.setAddress(Address);
	        int flag =  attendanceService.updateAttendanceGraph(rdto);
	        if(flag == 1){
	        	return "SUCCESS";
	        }
	        else{
	        	return "FAILED";
	        }
    }
	
	
	
	@RequestMapping(value = "/deleteAttendanceGraph", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public String deleteAttendance(@RequestParam(value="userId",required=true) String userId,
    		@RequestParam(value="createTime",required=false) String CreateTime) {  
			AttendanceGraphDTO rdto = new AttendanceGraphDTO();
			rdto.setUserId(userId);
			rdto.setCreateTime(CreateTime);
	        int flag =  attendanceService.deleteAttendanceGraph(rdto);
	        if(flag == 1){
	        	return "SUCCESS";
	        }
	        else{
	        	return "FAILED";
	        }
    }
	
}
