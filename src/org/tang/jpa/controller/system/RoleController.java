package org.tang.jpa.controller.system;

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
import org.tang.jpa.dto.system.RoleDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.system.ResourceService;
import org.tang.jpa.service.system.RoleService;
import org.tang.jpa.utils.MyConstants;
import org.tang.jpa.utils.Page;

@Controller("roleController")  
@RequestMapping("role")  
@SessionAttributes("currentUser")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryRole", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryRole(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="rolename",required=false) String roleName
    		,@RequestParam(value="page",required=false) int pageNo
    		,@RequestParam(value="rows",required=false) int pageSize) {  
        Map<String, Object> model = new HashMap<String, Object>();
        
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map params = new HashMap();
        
        if(!dto.getRoleId().equals("1")){
        	  params.put("orgId", dto.getOrgId());
        }
        params.put("roleName", roleName);
        page.setParams(params);
        Page p = roleService.findRole(page);
        model.put("rows", p.getResults());
        model.put("total", p.getTotalRecord());
        return model;  
    }  
	
	
	@RequestMapping(value = "/addRole", method = RequestMethod.POST)  
    @ResponseBody  
    public String addRole(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="roleid",required=false) String Roleid,
				@RequestParam(value="orgid",required=false) String Orgid,
				@RequestParam(value="rolename",required=false) String Rolename
    		) {  
	        RoleDTO rdto = new RoleDTO();
	        rdto.setRoleName(Rolename);
	        rdto.setOrgId(Orgid);
	        int flag =  roleService.insertRole(rdto);
	        if(flag == 1){
	        	return MyConstants.ADDSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.ADDFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/modifyRole", method = RequestMethod.POST)  
    @ResponseBody  
    public String modifyRole(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="roleid",required=false) String Roleid,
				@RequestParam(value="orgid",required=false) String Orgid,
				@RequestParam(value="rolename",required=false) String Rolename
    		) {  
	        RoleDTO rdto = new RoleDTO();
        		rdto.setRoleId(Roleid);
        	    rdto.setOrgId(Orgid);
        		rdto.setRoleName(Rolename);
	       
	        int flag =  roleService.updateRole(rdto);
	        if(flag == 1){
	        	return MyConstants.MODIFYSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.MODIFYFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/deleteRole", method = RequestMethod.POST)  
    @ResponseBody  
    public String deleteRole(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="roleid",required=true) String roleId) {  
			RoleDTO rdto = new RoleDTO();
	        int flag =  roleService.deleteRole(roleId);
	        if(flag == 1){
	        	return MyConstants.DELSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.DELFAIL.getName();
	        }
    }
	
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryRoleTree", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryRoleAuthTree(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="appointroleid",required=true) String appointRoleId) {  
        Map<String, Object> model = new HashMap<String, Object>();
        dto.setAppointRoleId(appointRoleId);
        List roleTree = roleService.findRoleAuthTree(dto);
        model.put("tree",roleTree);
        return model;  
    }  
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveRoleAuth", method = RequestMethod.POST)  
    @ResponseBody  
    public String saveRoleAuth(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="roleid",required=true) String roleid
    		,@RequestParam(value="resources",required=true) String resources) {  
        int flag =  roleService.saveRoleAuth(dto.getRoleId(), roleid,resources);
        if(flag != 0){
        	return MyConstants.AUTHSUCCESS.getName();
        }
        else{
        	return MyConstants.AUTHFAIL.getName();
        }
    }  
	
}
