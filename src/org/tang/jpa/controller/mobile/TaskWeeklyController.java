package org.tang.jpa.controller.mobile;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tang.jpa.dto.mobile.TaskWeeklyDTO;
import org.tang.jpa.service.mobile.TaskWeeklyService;
import org.tang.jpa.utils.MobileConstant;

@Controller("taskWeeklyMobileController")  
@RequestMapping("mobile")  
public class TaskWeeklyController {
	@Autowired
	private TaskWeeklyService taskWeeklyService;
	
	@RequestMapping(value = "/queryTaskWeekly", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public ModelMap queryTaskWeekly(
    		@RequestParam(value="type") String type
    		,@RequestParam(value="orgId",required=false) String orgId
    		,@RequestParam(value="createDate",required=false) String createDate) {  
		ModelMap mm = new ModelMap();
		TaskWeeklyDTO udto = new TaskWeeklyDTO();
        udto.setOrgId(orgId);
        udto.setType(type);
        udto.setCreateDate(createDate);
        
        List<TaskWeeklyDTO> dtoList = taskWeeklyService.queryTaskWeekly(udto);
        mm.put("sessionKey", "examTang");
        if(dtoList!=null){
        	mm.put("msgFlag", MobileConstant.taskweekly_query_success);
        	mm.put("response", dtoList);
        }
        else{
        	mm.put("msgFlag", MobileConstant.taskweekly_query_fail);
        }
        return mm;  //跳转  ;  
    }  
	
	
	@RequestMapping(value = "/addTaskWeekly",  method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public ModelMap addTaskWeekly(
				@RequestParam(value="id",required=false) String id,
				@RequestParam(value="authorId",required=false) String authorid,
				@RequestParam(value="authorName",required=false) String authorname,
				@RequestParam(value="type",required=false) String type,
				@RequestParam(value="title",required=false) String title,
				@RequestParam(value="content",required=false) String content,
				@RequestParam(value="createTime",required=false) String createtime
    		) {  
				ModelMap mm = new ModelMap();
		        mm.put("sessionKey", "examTang");
	        	TaskWeeklyDTO rdto = new TaskWeeklyDTO();
	        	rdto.setId(id);
	        	rdto.setAuthorId(authorid);
	        	rdto.setAuthorName(authorname);
	        	rdto.setType(type);
	        	rdto.setTitle(title);
	        	rdto.setContent(content);
	        	rdto.setCreateTime(createtime);
	       
//	        int flag =  taskWeeklyService.pushTaskWeeklyRealTime(rdto);
//	        if(flag == 1){
//	        	mm.put("msgFlag", MobileConstant.taskweekly_add_success);
//	        }
//	        else{
//	        	mm.put("msgFlag", MobileConstant.taskweekly_add_fail);
//	        }
	        return mm; 
    }
	
}
