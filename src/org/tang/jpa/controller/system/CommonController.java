package org.tang.jpa.controller.system;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.tang.jpa.dto.system.ResourceDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.system.CommonService;
import org.tang.jpa.utils.Page;


@Controller("commonController")  
@RequestMapping("common")  
@SessionAttributes("currentUser")
public class CommonController {
	
	@Autowired
	private CommonService commonService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryDictory", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryDictory(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="code",required=false) String code
    		,@RequestParam(value="page",required=false) int pageNo
    		,@RequestParam(value="rows",required=false) int pageSize) {  
        Map<String, Object> model = new HashMap<String, Object>();
        
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map params = new HashMap();
        params.put("code", code);
        page.setParams(params);
        Page p = commonService.queryDictory(page);
        model.put("rows", p.getResults());
        model.put("total", p.getTotalRecord());
        return model;  
    }  
	
	
}
