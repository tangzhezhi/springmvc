package org.tang.jpa.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.tang.jpa.dto.system.OrganizationDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.system.OrganizationService;
import org.tang.jpa.utils.MyConstants;
import org.tang.jpa.utils.Page;

@Controller("organizationController")  
@RequestMapping("organization")  
@SessionAttributes("currentUser")
public class OrganizationController {
	@Autowired
	private OrganizationService organizationService;
	
	@SuppressWarnings("unchecked")
	@RequiresPermissions("organization:view")
	@RequestMapping(value = "/queryOrganization", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryOrganization(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="organizationName",required=false) String organizationName
    		,@RequestParam(value="page",required=false) int pageNo
    		,@RequestParam(value="rows",required=false) int pageSize) {  
        Map<String, Object> model = new HashMap<String, Object>();
        
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map params = new HashMap();
        params.put("orgname", organizationName);
        if(!dto.getRoleId().equals("1")){
      	  params.put("orgid", dto.getOrgId());
        }
        page.setParams(params);
        Page p = organizationService.findOrganization(page);
        model.put("rows", p.getResults());
        model.put("total", p.getTotalRecord());
        return model;  
    }  
	
	@RequiresPermissions("organization:create")
	@RequestMapping(value = "/addOrganization", method = RequestMethod.POST)  
    @ResponseBody  
    public String addOrganization(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="orgname",required=false) String Orgname,
				@RequestParam(value="orgaddress",required=false) String Orgaddress,
				@RequestParam(value="orgcontact",required=false) String Orgcontact,
				@RequestParam(value="orgphone",required=false) String Orgphone,
				@RequestParam(value="orgemails",required=false) String Orgemails
    		) {  
	        OrganizationDTO rdto = new OrganizationDTO();
	        	rdto.setOrgname(Orgname);
	        	rdto.setOrgaddress(Orgaddress);
	        	rdto.setOrgcontact(Orgcontact);
	        	rdto.setOrgphone(Orgphone);
	        	rdto.setOrgemails(Orgemails);
	       
	        int flag =  organizationService.insertOrganization(rdto);
	        if(flag == 1){
	        	return MyConstants.ADDSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.ADDFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/modifyOrganization", method = RequestMethod.POST)  
    @ResponseBody  
    public String modifyOrganization(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="orgid",required=false) String Orgid,
				@RequestParam(value="orgname",required=false) String Orgname,
				@RequestParam(value="orgaddress",required=false) String Orgaddress,
				@RequestParam(value="orgcontact",required=false) String Orgcontact,
				@RequestParam(value="orgphone",required=false) String Orgphone,
				@RequestParam(value="orgemails",required=false) String Orgemails
    		) {  
	        OrganizationDTO rdto = new OrganizationDTO();
        		rdto.setOrgid(Orgid);
        		rdto.setOrgname(Orgname);
        		rdto.setOrgaddress(Orgaddress);
        		rdto.setOrgcontact(Orgcontact);
        		rdto.setOrgphone(Orgphone);
        		rdto.setOrgemails(Orgemails);
	       
	        int flag =  organizationService.updateOrganization(rdto);
	        if(flag == 1){
	        	return MyConstants.MODIFYSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.MODIFYFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/deleteOrganization", method = RequestMethod.POST)  
    @ResponseBody  
    public String deleteOrganization(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="organizationid",required=true) String organizationId) {  
			OrganizationDTO rdto = new OrganizationDTO();
	        int flag =  organizationService.deleteOrganization(organizationId);
	        if(flag == 1){
	        	return MyConstants.DELSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.DELFAIL.getName();
	        }
    }
	
}
