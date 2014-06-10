package org.tang.jpa.controller.mobile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tang.jpa.dto.system.NoticeDTO;
import org.tang.jpa.service.system.NoticeService;
import org.tang.jpa.utils.MobileConstant;

@Controller("noticeMobileController")  
@RequestMapping("mobile")  
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value = "/queryNotice", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public ModelMap queryNotice(
    		@RequestParam(value="type") String type
    		,@RequestParam(value="orgId",required=false) String orgId
    		,@RequestParam(value="createTime",required=false) String createTime) {  
		ModelMap mm = new ModelMap();
		NoticeDTO udto = new NoticeDTO();
        udto.setOrgId(orgId);
        udto.setType(type);
        udto.setCreateTime(createTime);
        
        List<NoticeDTO> dtoList = noticeService.queryNotice(udto);
        mm.put("sessionKey", "examTang");
        if(dtoList!=null){
        	mm.put("msgFlag", MobileConstant.notice_query_success);
        	mm.put("response", dtoList);
        }
        else{
        	mm.put("msgFlag", MobileConstant.notice_query_fail);
        }
        return mm;  //跳转  ;  
    }  
	
	
	@RequestMapping(value = "/addNotice",  method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public ModelMap addNotice(
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
	        	NoticeDTO rdto = new NoticeDTO();
	        	rdto.setId(id);
	        	rdto.setAuthorId(authorid);
	        	rdto.setAuthorName(authorname);
	        	rdto.setType(type);
	        	rdto.setTitle(title);
	        	rdto.setContent(content);
	        	rdto.setCreateTime(createtime);
	       
	        int flag =  noticeService.pushNoticeRealTime(rdto);
	        if(flag == 1){
	        	mm.put("msgFlag", MobileConstant.notice_add_success);
	        }
	        else{
	        	mm.put("msgFlag", MobileConstant.notice_add_fail);
	        }
	        return mm; 
    }
	
}
