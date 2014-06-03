
package org.tang.jpa.dao.mobile;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.mobile.ChatMsgDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface ChatMsgDao  {
	
    public int insertChatMsg(ChatMsgDTO dto);  
    
    public int insertRoleChatMsg(ChatMsgDTO dto);  
    
    public int updateChatMsg(ChatMsgDTO dto);  
     
    public int deleteChatMsg(String chatMsgId);  
     
    public Page<?> selectChatMsgAll(Page<?> page);  
    
    public List<ChatMsgDTO> selectChatMsgWaitPush();  
    
    public List<ChatMsgDTO> selectPushMachine(ChatMsgDTO dto);  
}
