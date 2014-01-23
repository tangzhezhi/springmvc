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
import org.tang.jpa.dto.system.ResourceDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.system.ResourceService;
import org.tang.jpa.utils.MyConstants;
import org.tang.jpa.utils.Page;


@Controller("resourceController")  
@RequestMapping("resource")  
@SessionAttributes("currentUser")
public class ResourceController {
	@Autowired
	private ResourceService resourceService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryResource", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryResource(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="resourceName",required=false) String resourceName
    		,@RequestParam(value="page",required=false) int pageNo
    		,@RequestParam(value="rows",required=false) int pageSize) {  
        Map<String, Object> model = new HashMap<String, Object>();
        
        ResourceDTO rdto = new ResourceDTO();
        rdto.setRoleId(dto.getRoleId());
        rdto.setResourceName(resourceName);
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map params = new HashMap();
        params.put("resourceName", resourceName);
        params.put("roleId", dto.getRoleId());
        page.setParams(params);
        Page p = resourceService.findResource(page);
        model.put("rows", p.getResults());
        model.put("total", p.getTotalRecord());
        return model;  
    }  
	
	
	@RequestMapping(value = "/addResource", method = RequestMethod.POST)  
    @ResponseBody  
    public String addResource(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="resourceName",required=false) String resourceName,
    		@RequestParam(value="resourceCode",required=false) String resourceCode,
    		@RequestParam(value="resourceParentId",required=false) String resourceParentId,
    		@RequestParam(value="resourceSort",required=false) String resourceSort,
    		@RequestParam(value="resourceType",required=false) String resourceType,
    		@RequestParam(value="resourceUrl",required=false) String resourceUrl
    		) {  
	        ResourceDTO rdto = new ResourceDTO();
	        rdto.setResourceName(resourceName);
	        rdto.setResourceCode(resourceCode);
	        rdto.setResourceParentId(resourceParentId);
	        rdto.setResourceSort(resourceSort);
	        rdto.setResourceType(resourceType);
	        rdto.setResourceUrl(resourceUrl);
	        rdto.setRoleId(dto.getRoleId());
	       
	        int flag =  resourceService.insertResource(rdto);
	        if(flag == 1){
	        	return MyConstants.ADDSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.ADDFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/modifyResource", method = RequestMethod.POST)  
    @ResponseBody  
    public String modifyResource(@ModelAttribute("currentUser") UserDTO dto,
    		@RequestParam(value="resourceId",required=true) String resourceId,
    		@RequestParam(value="resourceName",required=false) String resourceName,
    		@RequestParam(value="resourceCode",required=false) String resourceCode,
    		@RequestParam(value="resourceParentId",required=false) String resourceParentId,
    		@RequestParam(value="resourceSort",required=false) String resourceSort,
    		@RequestParam(value="resourceType",required=false) String resourceType,
    		@RequestParam(value="resourceUrl",required=false) String resourceUrl
    		) {  
	        ResourceDTO rdto = new ResourceDTO();
	        rdto.setResourceId(resourceId);
	        rdto.setResourceName(resourceName);
	        rdto.setResourceCode(resourceCode);
	        rdto.setResourceParentId(resourceParentId);
	        rdto.setResourceSort(resourceSort);
	        rdto.setResourceType(resourceType);
	        rdto.setResourceUrl(resourceUrl);
	        rdto.setRoleId(dto.getRoleId());
	       
	        int flag =  resourceService.updateResource(rdto);
	        if(flag == 1){
	        	return MyConstants.MODIFYSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.MODIFYFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/deleteResource", method = RequestMethod.POST)  
    @ResponseBody  
    public String deleteResource(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="resourceId",required=true) String resourceId) {  
			ResourceDTO rdto = new ResourceDTO();
			rdto.setRoleId(dto.getRoleId());
	        int flag =  resourceService.deleteResource(resourceId);
	        if(flag == 1){
	        	return MyConstants.DELSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.DELFAIL.getName();
	        }
    }
	
}
