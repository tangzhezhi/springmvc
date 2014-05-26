package org.tang.jpa.controller.mobile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tang.jpa.dto.mobile.MobileBaseRepDTO;
import org.tang.jpa.dto.mobile.MobileUserDTO;
import org.tang.jpa.dto.mobile.UserInfoDTO;
import org.tang.jpa.service.mobile.MobileUserService;
import org.tang.jpa.utils.MobileConstant;

import com.google.gson.Gson;

@Controller("contactController")  
@RequestMapping("mobile")  
public class ContactController {
	@Autowired
	private MobileUserService mobileUserService;
	
	@RequestMapping(value = "/queryContactUserInfo", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public String  queryContactUserInfo(@RequestParam(value="orgId") String orgId) {  
		String result = "";
		MobileUserDTO udto = new MobileUserDTO();
        udto.setOrgId(orgId);
        MobileBaseRepDTO mbt = new MobileBaseRepDTO();
        Gson gson = new Gson();  
        
        List<UserInfoDTO> dtoList = mobileUserService.queryContactUserInfo(udto);
        if(dtoList!=null){
        	mbt.setSessionKey("examTang");
        	mbt.setMsgFlag(MobileConstant.contact_query_success);
        	mbt.setResponse(gson.toJson(dtoList));
        }
        else{
        	mbt.setMsgFlag(MobileConstant.contact_query_fail);
        }
        result = gson.toJson(mbt);  
        return result;  //跳转  ;  
    }
}
