package org.tang.jpa.controller.mobile;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.tang.jpa.dto.mobile.ChatMsgDTO;
import org.tang.jpa.service.mobile.ChatMsgService;
import org.tang.jpa.utils.DateTool;
import org.tang.jpa.utils.MobileConstant;
import org.tang.jpa.utils.MyConstants;
import org.tang.jpa.utils.Page;

@Controller("chatMsgController")  
@RequestMapping("chatMsg")  
@SessionAttributes("currentUser")
public class ChatMsgController {
	@Autowired
	private ChatMsgService chatMsgService;
	
	
	@RequestMapping(value = "/addChatMsg", method = RequestMethod.POST)  
    @ResponseBody  
    public ModelMap addChatMsg(
				@RequestParam(value="fromUserId",required=false) String FromUserId,
				@RequestParam(value="toUserId",required=false) String ToUserId,
				@RequestParam(value="content",required=false) String Content
    		) {  
				ModelMap mm = new ModelMap();
		        mm.put("sessionKey", "examTang");
			    ChatMsgDTO rdto = new ChatMsgDTO();
	        	rdto.setId(UUID.randomUUID().toString());
	        	rdto.setFromUserId(FromUserId);
	        	rdto.setToUserId(ToUserId);
	        	rdto.setContent(Content);
	        	rdto.setCreateTime(DateTool.getDateStringYMDHMS(new Date()));
	        	rdto.setState("0");
	       
	        int flag =  chatMsgService.insertChatMsg(rdto);
	        

	        if(flag == 1){
	        	mm.put("msgFlag", MobileConstant.attendance_upload_success);
	        }
	        else{
	        	mm.put("msgFlag", MobileConstant.attendance_upload_fail);
	        }
	        return mm; 
    }
	
	
	
	
	
	
	

	
}
