/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.exam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.exam.ExampaperDao;
import org.tang.jpa.dto.exam.ExamPaperDetailsDTO;
import org.tang.jpa.dto.exam.ExampaperDTO;
import org.tang.jpa.dto.exam.OptionsDTO;
import org.tang.jpa.dto.system.ResourceDTO;
import org.tang.jpa.utils.Page;

@Service
public class ExampaperService {
	@Autowired
	private ExampaperDao exampaperDao;
	
	/**
	 * 分页
	 * @param udto
	 * @return
	 */
	public  Page findExampaper(Page page){
		Page  pageList = (Page) exampaperDao.selectExampaperAll(page);
		if(pageList!=null && pageList.getResults().size() > 0 ){
			pageList.setPageNo(page.getPageNo());
			pageList.setPageSize(page.getPageSize());
			pageList.setTotalPage(page.getTotalPage());
			pageList.setTotalRecord(page.getTotalRecord());
		}
		return pageList;
	}
	
	public int insertExampaper(ExampaperDTO dto){
		int flag = 0;
		if(dto!=null){
			flag = exampaperDao.insertExampaper(dto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int updateExampaper(ExampaperDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = exampaperDao.updateExampaper(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int deleteExampaper(String exampaperId){
		int flag = 0;
		if(exampaperId!=null){
			flag = exampaperDao.deleteExampaper(exampaperId);
		}
		else{
			flag = 0;
		}
		return flag;
	}

	public int addOptionsToExam(ExamPaperDetailsDTO rdto) {
		int flag = 0;
		if(rdto!=null){
			flag = exampaperDao.insertOptionsToExam(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}

	public int queryExamExistsOption(ExamPaperDetailsDTO rdto) {
		return exampaperDao.queryExamExistsOption(rdto);
	}

	public Page queryOptionsOfExam(Page page) {
		Page  pageList = (Page) exampaperDao.queryOptionsOfExam(page);
		if(pageList!=null && pageList.getResults().size() > 0 ){
			pageList.setPageNo(page.getPageNo());
			pageList.setPageSize(page.getPageSize());
			pageList.setTotalPage(page.getTotalPage());
			pageList.setTotalRecord(page.getTotalRecord());
		}
		return pageList;
	}

	public int deleteOneOptionsOfExampaper(ExamPaperDetailsDTO rdto) {
		int flag = 0;
		if(rdto!=null){
			flag = exampaperDao.deleteOneOptionsOfExampaper(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}

	public int modifyOneOptionsOfExampaper(ExamPaperDetailsDTO rdto) {
		int flag = 0;
		if(rdto!=null){
			flag = exampaperDao.modifyOneOptionsOfExampaper(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}

	public List previewExampaper(ExampaperDTO rdto) {
		List<OptionsDTO> resultList = null;
		if(rdto!=null){
			resultList = exampaperDao.previewExampaper(rdto);
		}
		return resultList;
		
	}

	public ExampaperDTO findExamInfo(ExamPaperDetailsDTO rdto) {
		return exampaperDao.findExamInfo(rdto);
	}

	public ExampaperDTO findExamInfoByExamId(String examid) {
		return exampaperDao.findExamInfoByExamId(examid);
	}
	
	
	
}
