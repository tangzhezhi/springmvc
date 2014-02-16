/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.exam;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.exam.ExamDao;
import org.tang.jpa.dao.exam.ExampaperDao;
import org.tang.jpa.dto.exam.UserexamDTO;
import org.tang.jpa.dto.exam.UserexamdetailsDTO;
import org.tang.jpa.dto.system.TreeDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.utils.Page;

@Service
public class ExamService {
	@Autowired
	private ExamDao examDao;
	
	@Autowired
	private ExampaperDao exampaperDao;
	
	
	public List<TreeDTO> findExamTree(UserDTO dto) {
		return examDao.findExamTree(dto);
	}

	
	public int saveUserExampaper(String userId,String examid, String answercontent) {
		int flag = 0 ; 
		List<UserexamdetailsDTO> uList = new ArrayList<UserexamdetailsDTO>();
		List<UserexamdetailsDTO> updateList = new ArrayList<UserexamdetailsDTO>();
		
		UserexamDTO userexamDTO = new UserexamDTO();
		
		userexamDTO.setUserexamid(UUID.randomUUID().toString());
		userexamDTO.setUserid(userId);
		userexamDTO.setExamid(examid);
		
		String[] options = answercontent.split("#");
		for(String option : options){
			UserexamdetailsDTO userexamdetailsDTO = new UserexamdetailsDTO();
			String[] optionContents = option.split("=");
			userexamdetailsDTO.setOptionid(optionContents[1]);
			userexamdetailsDTO.setUserAnswer(optionContents[3]);
			userexamdetailsDTO.setUserexamid(userexamDTO.getUserexamid());
			userexamdetailsDTO.setUserexamdetailsid(UUID.randomUUID().toString());
			uList.add(userexamdetailsDTO);
		}
		
		
		String userexamid = examDao.findUserExamId(userexamDTO);
		
		if(userexamid==null){
			try {
				flag = examDao.insertUserExam(userexamDTO);
				flag = examDao.insertUserExamDetails(uList);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			for(UserexamdetailsDTO uddto: uList){
				uddto.setUserexamid(userexamid);
				updateList.add(uddto);
			}
			flag = examDao.updateUserExamDetails(updateList);
		}
		return flag;
	}


	public List showExamInformationTopFive(String examType) {
		return exampaperDao.showExamInformationTopFive(examType);
	}
	
	
	public Page showExamInformationAllPage(Page page) {
		Page  pageList = (Page) exampaperDao.selectExampaperAll(page);
		if(pageList!=null && pageList.getResults().size() > 0 ){
			pageList.setPageNo(page.getPageNo());
			pageList.setPageSize(page.getPageSize());
			pageList.setTotalPage(page.getTotalPage());
			pageList.setTotalRecord(page.getTotalRecord());
		}
		return pageList;
	}
	
	
	
}
