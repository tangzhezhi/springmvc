package org.tang.jpa.controller.system;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.system.UserService;


@Controller("loginController")  
@RequestMapping("login")  
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/userLogin", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public ModelAndView  userLogin(HttpSession session,ModelAndView model,@RequestParam(value="userName") String userName,
    		@RequestParam(value="userPwd") String userPwd,
    		@RequestParam(value="orgid",required=false) String userOrgid
    		) {  
		UserDTO udto = new UserDTO();
        udto.setUserName(userName);
        udto.setUserPwd(userPwd);
        UserDTO dto = userService.verifyUserLoginInfo(udto);
        if(dto!=null){
        	 session.setAttribute("currentUser", dto);
        	 model.setViewName("redirect:/index.html");
        	 return model;  //跳转  ;  
        }
        else{
        	model.setViewName("redirect:/login.html");
        	return model;
        }
    }  
    
}
