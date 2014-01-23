package org.tang.jpa.service.system;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.system.OperateLogDao;
import org.tang.jpa.dao.system.UserDao;
import org.tang.jpa.dto.system.OperateLogDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.utils.Page;

@Service("operateLogService")
public class OperateLogService {
	
	@Autowired
	private OperateLogDao operateLogDao;
	
	@SuppressWarnings("unchecked")
	public Page findOperateLogPage(Page page) {
		List<UserDTO> list = null;
		Page page1 = new Page();
		try {
			page1 =  operateLogDao.selectOperateLogDTO(page);
			list = page1.getResults();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return page1;
	}
	
	
	
	public int addOperateLog(OperateLogDTO dto) {
		int flag = 0;
		List<OperateLogDTO> list = null;
		try {
				int id = operateLogDao.insertOperateLog(dto);
				System.out.println("标识是：：：：："+id);
				flag = 1;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
        return flag;
	}
	
}
