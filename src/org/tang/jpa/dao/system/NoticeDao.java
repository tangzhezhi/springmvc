
package org.tang.jpa.dao.system;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.system.NoticeDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface NoticeDao  {
	
    public int insertNotice(NoticeDTO dto);  
    
    public int insertRoleNotice(NoticeDTO dto);  
    
    public int updateNotice(NoticeDTO dto);  
     
    public int deleteNotice(String noticeId);  
     
    public Page<?> selectNoticeAll(Page<?> page);  
    
	public List<NoticeDTO> queryNotice(NoticeDTO udto);  
    
}
