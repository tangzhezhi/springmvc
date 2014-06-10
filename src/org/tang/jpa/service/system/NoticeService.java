/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.system;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.system.NoticeDao;
import org.tang.jpa.dto.mobile.ChatMsgDTO;
import org.tang.jpa.dto.mobile.MobileUserDTO;
import org.tang.jpa.dto.mobile.UserInfoDTO;
import org.tang.jpa.dto.system.NoticeDTO;
import org.tang.jpa.service.mobile.PushMsgService;
import org.tang.jpa.utils.Page;

@Service
public class NoticeService {
	@Autowired
	private NoticeDao noticeDao;
	
	
	@Autowired
	private PushMsgService  pushMsgService;
	/**
	 * 分页
	 * @param udto
	 * @return
	 */
	public  Page findNotice(Page page){
		Page  pageList = (Page) noticeDao.selectNoticeAll(page);
		if(pageList!=null && pageList.getResults().size() > 0 ){
			pageList.setPageNo(page.getPageNo());
			pageList.setPageSize(page.getPageSize());
			pageList.setTotalPage(page.getTotalPage());
			pageList.setTotalRecord(page.getTotalRecord());
		}
		return pageList;
	}
	
	
	public List<NoticeDTO> queryNotice(NoticeDTO udto) {
		
		List<NoticeDTO> udtoList = null;
		try {
			udtoList =  noticeDao.queryNotice(udto);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return udtoList;
	}

	
	public int pushNoticeRealTime(NoticeDTO dto){
		int flag = 0;
		if(dto!=null){
			flag = noticeDao.insertNotice(dto);
			
	        if(flag == 1){
		        flag = pushMsgService.pushBroadcastMessage(dto);
	        }
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int updateNotice(NoticeDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = noticeDao.updateNotice(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int deleteNotice(String noticeId){
		int flag = 0;
		if(noticeId!=null){
			flag = noticeDao.deleteNotice(noticeId);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
}
