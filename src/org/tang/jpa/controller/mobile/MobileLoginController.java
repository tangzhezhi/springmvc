package org.tang.jpa.controller.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

@Controller("mobileLoginController")  
@RequestMapping("mobile")  
public class MobileLoginController {
	@Autowired
	private MobileUserService mobileUserService;
	
//	@RequestMapping(value = "/userLogin", method = {RequestMethod.POST , RequestMethod.GET})  
//    @ResponseBody  
//    public String  userLogin(@RequestParam(value="userName") String userName,
//    		@RequestParam(value="userPwd") String userPwd) {  
//		String result = "";
//		MobileUserDTO udto = new MobileUserDTO();
//        udto.setUserName(userName);
//        udto.setUserPwd(userPwd);
//        MobileBaseRepDTO mbt = new MobileBaseRepDTO();
//        Gson gson = new Gson();  
//        
//        MobileUserDTO dto = mobileUserService.verifyMobileUserLoginInfo(udto);
//        if(dto!=null){
//        	mbt.setSessionKey("examTang");
//        	mbt.setMsgFlag(MobileConstant.login_success);
//        	mbt.setResponse(gson.toJson(dto));
//        }
//        else{
//        	mbt.setMsgFlag(MobileConstant.login_fail);
//        }
//        result = gson.toJson(mbt);  
//        return result;  //跳转  ;  
//    }
	
	@RequestMapping(value = "/userLogin", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public ModelMap  userLogin(@RequestParam(value="userName") String userName,
    		@RequestParam(value="userPwd") String userPwd) { 
		ModelMap mm = new ModelMap();
		MobileUserDTO udto = new MobileUserDTO();
        udto.setUserName(userName);
        udto.setUserPwd(userPwd);
        MobileUserDTO dto = mobileUserService.verifyMobileUserLoginInfo(udto);
    	mm.put("sessionKey", "examTang");
        if(dto!=null){
        	mm.put("msgFlag", MobileConstant.login_success);
        	mm.put("response", dto);
        }
        else{
        	mm.put("msgFlag", MobileConstant.login_fail);
        }
        return mm;  //跳转  ;  
    }
	
	
	
	@RequestMapping(value = "/addPushInfo", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public ModelMap  addPushInfo(@RequestParam(value="userId") String userId,
    		@RequestParam(value="pushUserId") String pushUserId,
    		@RequestParam(value="pushChannelId") String pushChannelId,
    		@RequestParam(value="deviceType") String deviceType) {  
		UserInfoDTO udto = new UserInfoDTO();
        udto.setPushChannelId(pushChannelId);
        udto.setPushUserId(pushUserId);
        udto.setDeviceType(deviceType);
        udto.setUserId(userId);
        int flag = 0;
        ModelMap mm = new ModelMap();
        flag = mobileUserService.addPushInfo(udto);
        if(flag==1){
        	mm.put("sessionKey", "examTang");
        	mm.put("msgFlag", MobileConstant.pushInfo_upload_success);
        }
        else{
        	mm.put("msgFlag", MobileConstant.pushInfo_upload_fail);
        }
        return mm;  //跳转  ;  
    }
	
	
}
