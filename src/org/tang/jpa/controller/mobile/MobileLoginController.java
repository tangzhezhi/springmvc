package org.tang.jpa.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tang.jpa.dto.mobile.MobileBaseRepDTO;
import org.tang.jpa.dto.mobile.MobileUserDTO;
import org.tang.jpa.service.mobile.MobileUserService;
import org.tang.jpa.utils.MobileConstant;

import com.google.gson.Gson;

@Controller("mobileLoginController")  
@RequestMapping("mobile")  
public class MobileLoginController {
	@Autowired
	private MobileUserService mobileUserService;
	
	@RequestMapping(value = "/userLogin", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public String  userLogin(@RequestParam(value="userName") String userName,
    		@RequestParam(value="userPwd") String userPwd) {  
		String result = "";
		MobileUserDTO udto = new MobileUserDTO();
        udto.setUserName(userName);
        udto.setUserPwd(userPwd);
        MobileBaseRepDTO mbt = new MobileBaseRepDTO();
        Gson gson = new Gson();  
        
        MobileUserDTO dto = mobileUserService.verifyMobileUserLoginInfo(udto);
        if(dto!=null){
        	mbt.setSessionKey("examTang");
        	mbt.setMsgFlag(MobileConstant.login_success);
        	mbt.setResponse(gson.toJson(dto));
        }
        else{
        	mbt.setMsgFlag(MobileConstant.login_fail);
        }
        result = gson.toJson(mbt);  
        return result;  //跳转  ;  
    }
}
