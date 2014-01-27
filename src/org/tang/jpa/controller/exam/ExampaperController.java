package org.tang.jpa.controller.exam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.tang.jpa.dto.exam.ExamPaperDetailsDTO;
import org.tang.jpa.dto.exam.ExampaperDTO;
import org.tang.jpa.dto.exam.OptionsDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.exam.ExampaperService;
import org.tang.jpa.utils.MyConstants;
import org.tang.jpa.utils.Page;

@Controller("exampaperController")  
@RequestMapping("exampaper")  
@SessionAttributes("currentUser")
public class ExampaperController {
	@Autowired
	private ExampaperService exampaperService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryExampaper", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryExampaper(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="exampapername",required=false) String exampaperName
    		,@RequestParam(value="page",required=false) int pageNo
    		,@RequestParam(value="rows",required=false) int pageSize) {  
        Map<String, Object> model = new HashMap<String, Object>();
        
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map params = new HashMap();
        if(!dto.getRoleId().equals("1")){
      	  params.put("orgid", dto.getOrgId());
        }
        params.put("examName", exampaperName);
        page.setParams(params);
        Page p = exampaperService.findExampaper(page);
        model.put("rows",p==null?0:p.getResults());
        model.put("total", p==null?0:p.getTotalRecord());
        return model;  
    }  
	
	
	@RequestMapping(value = "/addExampaper", method = RequestMethod.POST)  
    @ResponseBody  
    public String addExampaper(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="examid",required=false) String Examid,
				@RequestParam(value="examName",required=false) String ExamName,
				@RequestParam(value="examOperater",required=false) String ExamOperater,
				@RequestParam(value="examTime",required=false) String ExamTime,
				@RequestParam(value="examSubject",required=false) String ExamSubject
    		) {  
	        ExampaperDTO rdto = new ExampaperDTO();
	        	rdto.setOrgid(dto.getOrgId());
	        	rdto.setExamid(Examid);
	        	rdto.setExamName(ExamName);
	        	rdto.setExamOperater(ExamOperater);
	        	rdto.setExamTime(ExamTime);
	        	rdto.setExamSubject(ExamSubject);
	       
	        int flag =  exampaperService.insertExampaper(rdto);
	        if(flag == 1){
	        	return MyConstants.ADDSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.ADDFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/modifyExampaper", method = RequestMethod.POST)  
    @ResponseBody  
    public String modifyExampaper(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="examid",required=false) String Examid,
				@RequestParam(value="examName",required=false) String ExamName,
				@RequestParam(value="examOperater",required=false) String ExamOperater,
				@RequestParam(value="examTime",required=false) String ExamTime,
				@RequestParam(value="examSubject",required=false) String ExamSubject
    		) {  
	        ExampaperDTO rdto = new ExampaperDTO();
        		rdto.setExamid(Examid);
        		rdto.setExamName(ExamName);
        		rdto.setExamOperater(ExamOperater);
        		rdto.setExamTime(ExamTime);
        		rdto.setExamSubject(ExamSubject);
	       
	        int flag =  exampaperService.updateExampaper(rdto);
	        if(flag == 1){
	        	return MyConstants.MODIFYSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.MODIFYFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/deleteExampaper", method = RequestMethod.POST)  
    @ResponseBody  
    public String deleteExampaper(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="examid",required=true) String exampaperId) {  
			ExampaperDTO rdto = new ExampaperDTO();
	        int flag =  exampaperService.deleteExampaper(exampaperId);
	        if(flag == 1){
	        	return MyConstants.DELSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.DELFAIL.getName();
	        }
    }
	
	
	@RequestMapping(value = "/addOptionsToExam", method = RequestMethod.POST)  
    @ResponseBody  
    public String addOptionsToExam(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="examid",required=true) String exampaperId
    		,@RequestParam(value="optionid",required=true) String optionId) {  
			ExamPaperDetailsDTO rdto = new ExamPaperDetailsDTO();
			rdto.setExamid(exampaperId);
			rdto.setOptionid(optionId);
			
			int nums = exampaperService.queryExamExistsOption(rdto);
			
			if(nums == 0){
		        int flag =  exampaperService.addOptionsToExam(rdto);
		        if(flag == 1){
		        	return MyConstants.ADDSUCCESS.getName();
		        }
		        else{
		        	return MyConstants.ADDFAIL.getName();
		        }
			}
			else{
				return MyConstants.EXIST.getName();
			}
    }
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryOptionsOfExam", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryOptionsOfExam(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="optionname",required=false) String optionName
    		,@RequestParam(value="examid",required=false) String examid
    		,@RequestParam(value="page",required=false) int pageNo
    		,@RequestParam(value="rows",required=false) int pageSize) {  
        Map<String, Object> model = new HashMap<String, Object>();
        
        Page page = new Page();
        Page p = null;
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map params = new HashMap();
        params.put("examid", examid);
        page.setParams(params);
        if(!("").equals(examid) && null != examid){
        	 p = exampaperService.queryOptionsOfExam(page);
        }
        model.put("rows",p==null?0:p.getResults());
        model.put("total", p==null?0:p.getTotalRecord());
        return model;  
    }  
	
	
	
	
	@RequestMapping(value = "/deleteOneOptionsOfExampaper", method = RequestMethod.POST)  
    @ResponseBody  
    public String deleteOneOptionsOfExampaper(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="exampaperdetailsid",required=true) String exampaperdetailsid
    		) {  
			ExamPaperDetailsDTO rdto = new ExamPaperDetailsDTO();
			rdto.setExampaperdetailsid(exampaperdetailsid);
	        int flag =  exampaperService.deleteOneOptionsOfExampaper(rdto);
	        if(flag == 1){
	        	return MyConstants.DELSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.DELFAIL.getName();
	        }
    }
	
	
	@RequestMapping(value = "/modifyOneOptionsOfExampaper", method = RequestMethod.POST)  
    @ResponseBody  
    public String modifyOneOptionsOfExampaper(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="exampaperdetailsid",required=true) String exampaperdetailsid
    		,@RequestParam(value="optionScore",required=true) String optionScore
    		) {  
			ExamPaperDetailsDTO rdto = new ExamPaperDetailsDTO();
			rdto.setExampaperdetailsid(exampaperdetailsid);
			rdto.setOptionScore(optionScore);
	        int flag =  exampaperService.modifyOneOptionsOfExampaper(rdto);
	        if(flag == 1){
	        	return MyConstants.MODIFYSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.MODIFYFAIL.getName();
	        }
    }
	
	
	@RequestMapping(value = "/previewExampaper", method = RequestMethod.POST)  
    @ResponseBody  
    public  Map<String, Object>  previewExampaper(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="examid",required=true) String examid
    		) {  
		 	Map<String, Object> model = new HashMap<String, Object>();
			ExampaperDTO rdto = new ExampaperDTO();
			rdto.setExamid(examid);
	        List<OptionsDTO> result =  exampaperService.previewExampaper(rdto);
	        model.put("data", result);
	        return model;
    }
	
	
}
