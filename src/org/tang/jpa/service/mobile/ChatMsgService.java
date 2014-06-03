/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.mobile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.mobile.ChatMsgDao;
import org.tang.jpa.dto.mobile.ChatMsgDTO;
import org.tang.jpa.utils.MobileConstant;
import org.tang.jpa.utils.Page;

@Service
public class ChatMsgService {
	@Autowired
	private ChatMsgDao chatMsgDao;
	
	@Autowired
	private PushMsgService  pushMsgService;
	
	/**
	 * 分页
	 * @param udto
	 * @return
	 */
	public  Page findChatMsg(Page page){
		Page  pageList = (Page) chatMsgDao.selectChatMsgAll(page);
		pageList.setPageNo(page.getPageNo());
		pageList.setPageSize(page.getPageSize());
		pageList.setTotalPage(page.getTotalPage());
		pageList.setTotalRecord(page.getTotalRecord());
		return pageList;
	}
	
	
	public ChatMsgDTO selectPushMachine(ChatMsgDTO dto){
		ChatMsgDTO c = new ChatMsgDTO();
		List<ChatMsgDTO> l = chatMsgDao.selectPushMachine(dto);
		if(l!=null && l.size() > 0){
			c = l.get(0);
		}
		return c;
	}
	
	
	public int insertChatMsg(ChatMsgDTO dto){
		int flag = 0;
		if(dto!=null){
			flag = chatMsgDao.insertChatMsg(dto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int updateChatMsg(ChatMsgDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = chatMsgDao.updateChatMsg(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int deleteChatMsg(String chatMsgId){
		int flag = 0;
		if(chatMsgId!=null){
			flag = chatMsgDao.deleteChatMsg(chatMsgId);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	public int pushMsgRealTime(ChatMsgDTO dto){
        int flag =  this.insertChatMsg(dto);
        if(flag == 1){
	        ChatMsgDTO c = this.selectPushMachine(dto);
	        
	        dto.setPushChannelId(c.getPushChannelId());
	        dto.setPushuserId(c.getPushuserId());
	        flag = pushMsgService.pushMsgRealTime(dto);
        }
		return flag;
	}
	
	
}
