package org.tang.jpa.controller.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.system.UserService;


@Controller("loginController")  
@RequestMapping("login")  
public class LoginController {
	private  Logger  logger  = Logger.getLogger("LoginController");
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/userLogin", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public ModelAndView  userLogin(HttpSession session,ModelAndView model,@RequestParam(value="userName",required=false) String userName,
    		@RequestParam(value="userPwd",required=false) String userPwd,
    		@RequestParam(value="orgid",required=false) String userOrgid
    		) {  
		Subject currentUser = SecurityUtils.getSubject();
		HttpServletRequest request =  ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		AttributePrincipal principal = (AttributePrincipal)request.getUserPrincipal();   
		
		if (!currentUser.isAuthenticated()) {
			
			if(principal!=null){
				if(StringUtils.isBlank(userName)){
					userName = principal.getName();
				}
				if(StringUtils.isBlank(userPwd)){
					if(principal.getAttributes().size()>0){
						userPwd = principal.getAttributes().get("password").toString();
					}
				}
			}
			
			UsernamePasswordToken token = new UsernamePasswordToken(userName,
					"21232f297a57a5a743894a0e4a801fc3");
			token.setRememberMe(false);
			String ret="";
			try {
				currentUser.login(token);
				ret = "{success:true,message:'登陆成功'}";
				model.setViewName("redirect:/index.html");
			} catch (UnknownAccountException ex) {
				ret = "{success:false,message:'账号错误'}";
				logger.debug(ret);
				model.setViewName("redirect:/login.html");
			} catch (IncorrectCredentialsException ex) {
				ret = "{success:false,message:'密码错误'}";
				logger.debug(ret);
				model.setViewName("redirect:/login.html");
			} catch (LockedAccountException ex) {
				ret = "{success:false,message:'账号已被锁定，请与管理员联系'}";
				logger.debug(ret);
				model.setViewName("redirect:/login.html");
			} catch (AuthenticationException ex) {
				ret = "{success:false,message:'您没有授权'}";
				logger.debug(ret+":::"+ex);
				model.setViewName("redirect:/login.html");
			}
		}
		else{
			model.setViewName("redirect:/index.html");
		}
		 return model;  //跳转  
		
		
//		UserDTO udto = new UserDTO();
//        udto.setUserName(userName);
//        udto.setUserPwd(userPwd);
//        UserDTO dto = userService.verifyUserLoginInfo(udto);
//        if(dto!=null){
//        	 session.setAttribute("currentUser", dto);
//        	 model.setViewName("redirect:/index.html");
//        	 return model;  //跳转  ;  
//        }
//        else{
//        	model.setViewName("redirect:/login.html");
//        	return model;
//        }
    }
	
	
	@RequestMapping(value = "/userLoginOut", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public ModelAndView  userLoginOut(HttpSession session,
    		ModelAndView model,@ModelAttribute("currentUser") UserDTO dto) {  
		session.removeAttribute("currentUser");
		model.setViewName("redirect:/login.html");
		return model;  //跳转  ;  
    }  
	
    
}
