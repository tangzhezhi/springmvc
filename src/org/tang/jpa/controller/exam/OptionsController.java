package org.tang.jpa.controller.exam;

import java.util.Date;
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
import org.tang.jpa.dto.exam.OptionsDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.exam.OptionsService;
import org.tang.jpa.utils.DateTool;
import org.tang.jpa.utils.MyConstants;
import org.tang.jpa.utils.Page;

@Controller("optionsController")  
@RequestMapping("options")  
@SessionAttributes("currentUser")
public class OptionsController {
	@Autowired
	private OptionsService optionsService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryOptions", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryOptions(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="optionsname",required=false) String optionsName
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
        params.put("optionsTitle", optionsName);
        page.setParams(params);
        Page p = optionsService.findOptions(page);
        model.put("rows",p==null?0:p.getResults());
        model.put("total", p==null?0:p.getTotalRecord());
        return model;  
    }  
	
	
	@RequestMapping(value = "/addOptions", method = RequestMethod.POST)  
    @ResponseBody  
    public String addOptions(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="optionid",required=false) String Optionid,
				@RequestParam(value="optionContents",required=false) String Optioncontents,
				@RequestParam(value="optionAnswer",required=false) String Optionanswer,
				@RequestParam(value="optionType",required=false) String Optiontype,
				@RequestParam(value="optionTitle",required=false) String Optiontitle,
				@RequestParam(value="optionKnowledgePoint",required=false) String OptionknowledgePoint,
				@RequestParam(value="optionDate",required=false) String Optiondate,
				@RequestParam(value="optionLevelDifficult",required=false) String Optionleveldifficult
    		) {  
	        OptionsDTO rdto = new OptionsDTO();
	        	rdto.setOptionid(Optionid);
	        	rdto.setOptionContents(Optioncontents);
	        	rdto.setOptionAnswer(Optionanswer);
	        	rdto.setOptionType(Optiontype);
	        	rdto.setOptionTitle(Optiontitle);
	        	rdto.setOptionKnowledgePoint(OptionknowledgePoint);
	        	rdto.setOptionDate(Optiondate);
	        	rdto.setOptionLevelDifficult(Optionleveldifficult);
	        	rdto.setOrgid(dto.getOrgId());
	        	rdto.setUpdater(dto.getUserId());
	        	rdto.setUpdatetime(DateTool.getDateStringYMD(new Date()));
	       
	        int flag =  optionsService.insertOptions(rdto);
	        if(flag == 1){
	        	return MyConstants.ADDSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.ADDFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/modifyOptions", method = RequestMethod.POST)  
    @ResponseBody  
    public String modifyOptions(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="optionid",required=false) String Optionid,
				@RequestParam(value="optionContents",required=false) String Optioncontents,
				@RequestParam(value="optionAnswer",required=false) String Optionanswer,
				@RequestParam(value="optionType",required=false) String Optiontype,
				@RequestParam(value="optionTitle",required=false) String Optiontitle,
				@RequestParam(value="optionKnowledgePoint",required=false) String OptionknowledgePoint,
				@RequestParam(value="optionDate",required=false) String Optiondate,
				@RequestParam(value="optionLevelDifficult",required=false) String Optionleveldifficult
    		) {  
	        OptionsDTO rdto = new OptionsDTO();
        		rdto.setOptionid(Optionid);
	        	rdto.setOptionContents(Optioncontents);
	        	rdto.setOptionAnswer(Optionanswer);
	        	rdto.setOptionType(Optiontype);
	        	rdto.setOptionTitle(Optiontitle);
	        	rdto.setOptionKnowledgePoint(OptionknowledgePoint);
	        	rdto.setOptionDate(Optiondate);
	        	rdto.setOptionLevelDifficult(Optionleveldifficult);
	       
	        int flag =  optionsService.updateOptions(rdto);
	        if(flag == 1){
	        	return MyConstants.MODIFYSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.MODIFYFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/deleteOptions", method = RequestMethod.POST)  
    @ResponseBody  
    public String deleteOptions(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="optionsid",required=true) String optionsId) {  
			OptionsDTO rdto = new OptionsDTO();
	        int flag =  optionsService.deleteOptions(optionsId);
	        if(flag == 1){
	        	return MyConstants.DELSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.DELFAIL.getName();
	        }
    }
	
}
