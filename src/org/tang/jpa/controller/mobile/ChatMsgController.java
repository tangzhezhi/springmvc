package org.tang.jpa.controller.mobile;

import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tang.jpa.dto.mobile.ChatMsgDTO;
import org.tang.jpa.service.mobile.ChatMsgService;
import org.tang.jpa.utils.DateTool;
import org.tang.jpa.utils.MobileConstant;

@Controller("chatMsgController")  
@RequestMapping("mobile")  
public class ChatMsgController {
	@Autowired
	private ChatMsgService chatMsgService;
	
	@RequestMapping(value = "/addChatMsg", method = RequestMethod.POST)  
    @ResponseBody  
    public ModelMap addChatMsg(
				@RequestParam(value="fromUserId",required=false) String FromUserId,
				@RequestParam(value="toUserId",required=false) String ToUserId,
				@RequestParam(value="fromUserName",required=false) String fromUserName,
				@RequestParam(value="toUserName",required=false) String toUserName,
				@RequestParam(value="msgType",required=false) String msgType,
				@RequestParam(value="content",required=false) String Content
    		) {  
		  		int flag =	0;
				ModelMap mm = new ModelMap();
		        mm.put("sessionKey", "examTang");
			    ChatMsgDTO rdto = new ChatMsgDTO();
	        	rdto.setId(UUID.randomUUID().toString());
	        	rdto.setFromUserId(FromUserId);
	        	rdto.setToUserId(ToUserId);
	        	rdto.setContent(Content);
	        	rdto.setCreateTime(DateTool.getDateStringYMDHMS(new Date()));
	        	rdto.setState("0");
	        	rdto.setFromUserName(fromUserName);
	        	rdto.setToUserName(toUserName);
	        	rdto.setMsgType(msgType);
	        	
	        	flag = chatMsgService.pushMsgRealTime(rdto);
		        if(flag == 1){
			        	mm.put("msgFlag", MobileConstant.chat_msg_send_success);
		        }
		        else{
		        	mm.put("msgFlag", MobileConstant.chat_msg_send_fail);
		        }
	        return mm; 
    	}
	
	
	

	
}
