/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.mobile;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.mobile.TaskWeeklyDao;
import org.tang.jpa.dto.mobile.TaskWeeklyDTO;
import org.tang.jpa.utils.Page;

@Service
public class TaskWeeklyService {
	@Autowired
	private TaskWeeklyDao taskWeeklyDao;
	
	
	@Autowired
	private PushMsgService  pushMsgService;
	/**
	 * 分页
	 * @param udto
	 * @return
	 */
	public  Page findTaskWeekly(Page page){
		Page  pageList = (Page) taskWeeklyDao.selectTaskWeeklyAll(page);
		if(pageList!=null && pageList.getResults().size() > 0 ){
			pageList.setPageNo(page.getPageNo());
			pageList.setPageSize(page.getPageSize());
			pageList.setTotalPage(page.getTotalPage());
			pageList.setTotalRecord(page.getTotalRecord());
		}
		return pageList;
	}
	
	
	public List<TaskWeeklyDTO> queryTaskWeekly(TaskWeeklyDTO udto) {
		
		List<TaskWeeklyDTO> udtoList = null;
		try {
			udtoList =  taskWeeklyDao.queryTaskWeekly(udto);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return udtoList;
	}

	
//	public int pushTaskWeeklyRealTime(TaskWeeklyDTO dto){
//		int flag = 0;
//		if(dto!=null){
//			flag = taskWeeklyDao.insertTaskWeekly(dto);
//			
//	        if(flag == 1){
//		        flag = pushMsgService.pushBroadcastMessage(dto);
//	        }
//		}
//		else{
//			flag = 0;
//		}
//		return flag;
//	}
	
	
	public int updateTaskWeekly(TaskWeeklyDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = taskWeeklyDao.updateTaskWeekly(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int deleteTaskWeekly(String taskWeeklyId){
		int flag = 0;
		if(taskWeeklyId!=null){
			flag = taskWeeklyDao.deleteTaskWeekly(taskWeeklyId);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
}
