package org.tang.jpa.controller.publicInformation;

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
import org.tang.jpa.dto.publicInformation.EmailDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.publicInformation.EmailService;
import org.tang.jpa.utils.MyConstants;
import org.tang.jpa.utils.Page;

@Controller("emailController")  
@RequestMapping("email")  
@SessionAttributes("currentUser")
public class EmailController {
	@Autowired
	private EmailService emailService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryEmail", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryEmail(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="emailname",required=false) String emailName
    		,@RequestParam(value="page",required=false) int pageNo
    		,@RequestParam(value="rows",required=false) int pageSize) {  
        Map<String, Object> model = new HashMap<String, Object>();
        
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map params = new HashMap();
        params.put("subject", emailName);
        page.setParams(params);
        Page p = emailService.findEmail(page);
        model.put("rows",p==null?0:p.getResults());
        model.put("total", p==null?0:p.getTotalRecord());
        return model;  
    }  
	
	
	@RequestMapping(value = "/addEmail", method = RequestMethod.POST)  
    @ResponseBody  
    public String addEmail(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="emailid",required=false) String emailid,
				@RequestParam(value="fromadd",required=false) String fromadd,
				@RequestParam(value="subject",required=false) String subject,
				@RequestParam(value="content",required=false) String content,
				@RequestParam(value="status",required=false) String status,
				@RequestParam(value="toadd",required=false) String toadd,
				@RequestParam(value="attchfileurl",required=false) String attchfileurl
    		) {  
	        	EmailDTO rdto = new EmailDTO();
	        	rdto.setEmailid(UUID.randomUUID().toString());
	        	rdto.setFromadd(fromadd);
	        	rdto.setSubject(subject);
	        	rdto.setContent(content);
	        	rdto.setStatus(status);
	        	rdto.setToadd(toadd);
	        	rdto.setAttchfileurl(attchfileurl);
	       
	        int flag =  emailService.insertEmail(rdto);
	        if(flag == 1){
	        	return MyConstants.ADDSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.ADDFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/modifyEmail", method = RequestMethod.POST)  
    @ResponseBody  
    public String modifyEmail(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="emailid",required=false) String emailid,
				@RequestParam(value="fromadd",required=false) String fromadd,
				@RequestParam(value="subject",required=false) String subject,
				@RequestParam(value="content",required=false) String content,
				@RequestParam(value="status",required=false) String status,
				@RequestParam(value="toadd",required=false) String toadd,
				@RequestParam(value="attchfileurl",required=false) String attchfileurl
    		) {  
	        EmailDTO rdto = new EmailDTO();
        		rdto.setEmailid(emailid);
        		rdto.setFromadd(fromadd);
        		rdto.setSubject(subject);
        		rdto.setContent(content);
        		rdto.setStatus(status);
        		rdto.setToadd(toadd);
        		rdto.setAttchfileurl(attchfileurl);
	       
	        int flag =  emailService.updateEmail(rdto);
	        if(flag == 1){
	        	return MyConstants.MODIFYSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.MODIFYFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/deleteEmail", method = RequestMethod.POST)  
    @ResponseBody  
    public String deleteEmail(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="emailid",required=true) String emailId) {  
			EmailDTO rdto = new EmailDTO();
	        int flag =  emailService.deleteEmail(emailId);
	        if(flag == 1){
	        	return MyConstants.DELSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.DELFAIL.getName();
	        }
    }
	
}
