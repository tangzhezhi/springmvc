/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.mobile.ChatMsgDao;
import org.tang.jpa.dto.mobile.ChatMsgDTO;
import org.tang.jpa.utils.Page;

@Service
public class ChatMsgService {
	@Autowired
	private ChatMsgDao chatMsgDao;
	
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
	
	
	
}
