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
import org.tang.jpa.dto.exam.UserexamDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.exam.ExamService;

@Controller("examController")  
@RequestMapping("exam")  
@SessionAttributes("currentUser")
public class ExamController {
	@Autowired
	private ExamService examService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryExamTree", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryExamTree(@ModelAttribute("currentUser") UserDTO dto) {  
        Map<String, Object> model = new HashMap<String, Object>();
        List examTree = examService.findExamTree(dto);
        model.put("tree",examTree);
        return model;  
    }  
	
	@RequestMapping(value = "/saveUserExampaper", method = RequestMethod.POST)  
    @ResponseBody  
	public String saveUserExampaper(@ModelAttribute("currentUser") UserDTO dto
			,@RequestParam(value="examid",required=false) String examid
			,@RequestParam(value="answercontent",required=false) String answercontent){
		int flag = examService.saveUserExampaper(dto.getUserId(),examid,answercontent);
		
		return "";
	}
}
