package org.tang.jpa.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.tang.jpa.dto.system.DepartmentDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.system.DepartmentService;
import org.tang.jpa.utils.MyConstants;
import org.tang.jpa.utils.Page;

@Controller("departmentController")  
@RequestMapping("department")  
@SessionAttributes("currentUser")
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryDepartment", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryDepartment(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="departmentname",required=false) String departmentName
    		,@RequestParam(value="page",required=false) int pageNo
    		,@RequestParam(value="rows",required=false) int pageSize) {  
        Map<String, Object> model = new HashMap<String, Object>();
        
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map params = new HashMap();
        params.put("departmentname", departmentName);
        params.put("orgid", dto.getOrgId());
        params.put("departmentid", dto.getDepartmentId());
        page.setParams(params);
        Page p = departmentService.findDepartment(page);
        model.put("rows",p==null?0:p.getResults());
        model.put("total", p==null?0:p.getTotalRecord());
        return model;  
    }  
	
	
	
	@RequestMapping(value = "/queryUpperDepartment", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryUpperDepartment(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="departmentname",required=false) String departmentName
    		,@RequestParam(value="page",required=false) int pageNo
    		,@RequestParam(value="rows",required=false) int pageSize) {  
        Map<String, Object> model = new HashMap<String, Object>();
        
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map params = new HashMap();
        params.put("orgid", dto.getOrgId());
        page.setParams(params);
        Page p = departmentService.queryUpperDepartment(page);
        model.put("rows",p==null?0:p.getResults());
        model.put("total", p==null?0:p.getTotalRecord());
        return model;  
    }  
	
	
	
	@RequestMapping(value = "/addDepartment", method = RequestMethod.POST)  
    @ResponseBody  
    public String addDepartment(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="departmentid",required=false) String departmentid,
				@RequestParam(value="upperdepartmentid",required=false) String upperdepartmentid,
				@RequestParam(value="departmentname",required=false) String departmentname
    		) {  
	        DepartmentDTO rdto = new DepartmentDTO();
	        	rdto.setDepartmentid(UUID.randomUUID().toString());
	        	rdto.setOrgid(dto.getOrgId());
	        	rdto.setPdepartmentid(upperdepartmentid);
	        	rdto.setDepartmentname(departmentname);
	       
	        int flag =  departmentService.insertDepartment(rdto);
	        if(flag == 1){
	        	return MyConstants.ADDSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.ADDFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/modifyDepartment", method = RequestMethod.POST)  
    @ResponseBody  
    public String modifyDepartment(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="departmentid",required=false) String departmentid,
				@RequestParam(value="upperdepartmentid",required=false) String upperdepartmentid,
				@RequestParam(value="departmentname",required=false) String departmentname
    		) {  
	        DepartmentDTO rdto = new DepartmentDTO();
        		rdto.setDepartmentid(departmentid);
        		rdto.setOrgid(dto.getOrgId());
        		rdto.setDepartmentname(departmentname);
        		rdto.setPdepartmentid(upperdepartmentid);
	       
	        int flag =  departmentService.updateDepartment(rdto);
	        if(flag == 1){
	        	return MyConstants.MODIFYSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.MODIFYFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/deleteDepartment", method = RequestMethod.POST)  
    @ResponseBody  
    public String deleteDepartment(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="departmentid",required=true) String departmentId) {  
			DepartmentDTO rdto = new DepartmentDTO();
	        int flag =  departmentService.deleteDepartment(departmentId);
	        if(flag == 1){
	        	return MyConstants.DELSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.DELFAIL.getName();
	        }
    }
	
}
