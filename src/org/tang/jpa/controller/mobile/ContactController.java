package org.tang.jpa.controller.mobile;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tang.jpa.dto.mobile.MobileUserDTO;
import org.tang.jpa.dto.mobile.UserInfoDTO;
import org.tang.jpa.service.mobile.MobileUserService;
import org.tang.jpa.utils.MobileConstant;

@Controller("contactController")  
@RequestMapping("mobile")  
public class ContactController {
	@Autowired
	private MobileUserService mobileUserService;
	
	@RequestMapping(value = "/queryContactUserInfo", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public ModelMap   queryContactUserInfo(@RequestParam(value="orgId") String orgId) {  
		ModelMap mm = new ModelMap();
		MobileUserDTO udto = new MobileUserDTO();
        udto.setOrgId(orgId);
        
        List<UserInfoDTO> dtoList = mobileUserService.queryContactUserInfo(udto);
        if(dtoList!=null){
        	mm.put("sessionKey", "examTang");
        	mm.put("msgFlag", MobileConstant.contact_query_success);
        	mm.put("response", dtoList);
        }
        else{
        	mm.put("msgFlag", MobileConstant.contact_query_fail);
        }
        return mm;  //跳转  ;  
    }
}
