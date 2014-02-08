package org.tang.jpa.controller.publicInformation;

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
import org.tang.jpa.dto.exam.ExampaperDTO;
import org.tang.jpa.dto.exam.OptionsDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.exam.ExamService;
import org.tang.jpa.service.exam.ExampaperService;
import org.tang.jpa.service.publicInformation.ArticleService;


@Controller("publicIndexController")  
@RequestMapping("public")  
public class PublicIndexController {
	@Autowired
	private ExamService examService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ExampaperService exampaperService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/showExamInformationTopFive", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> showExamInformationTopFive() {  
        Map<String, Object> model = new HashMap<String, Object>();
        List examInfo = examService.showExamInformationTopFive();
        model.put("data",examInfo);
        return model;  
    }  
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/showArticleInformationTopFive", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> showArticleInformationTopFive() {  
        Map<String, Object> model = new HashMap<String, Object>();
        List articleInfo = articleService.showArticleInformationTopFive();
        model.put("data",articleInfo);
        return model;  
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
