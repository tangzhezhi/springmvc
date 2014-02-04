package org.tang.jpa.service.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.system.CommonDao;
import org.tang.jpa.utils.Page;
@Service
public class CommonService {
	@Autowired
	private CommonDao commonDao;
	
	public Page queryDictory(Page page) {
		Page  pageList = (Page) commonDao.selectDictory(page);
		if(pageList!=null && pageList.getResults().size() > 0 ){
			pageList.setPageNo(page.getPageNo());
			pageList.setPageSize(page.getPageSize());
			pageList.setTotalPage(page.getTotalPage());
			pageList.setTotalRecord(page.getTotalRecord());
		}
		return pageList;
	}
	
}
