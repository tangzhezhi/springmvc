
package org.tang.jpa.dao.publicInformation;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.publicInformation.VideoDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface VideoDao  {
	
    public int insertVideo(VideoDTO dto);  
    
    public int insertRoleVideo(VideoDTO dto);  
    
    public int updateVideo(VideoDTO dto);  
     
    public int deleteVideo(String videoId);  
     
    public Page<?> selectVideoAll(Page<?> page);

	public List findVideoTree();  
    
}
