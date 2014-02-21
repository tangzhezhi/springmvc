package org.tang.jpa.interceptor;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.tang.jpa.dto.system.OperateLogDTO;
import org.tang.jpa.dto.system.TreeDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.system.OperateLogService;
import org.tang.jpa.service.system.RoleService;
import org.tang.jpa.utils.DateTool;

public class AllInterceptor implements HandlerInterceptor {
	
	  private Logger logger = Logger.getLogger(AllInterceptor.class.getName());
	  
	  @Autowired
	  private OperateLogService operateLogService;
	  
	  @Autowired
	  private RoleService roleService;
	  
	  
	    @Override
	    public boolean preHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o) throws Exception {
	        UserDTO user=(UserDTO) hsr.getSession().getAttribute("currentUser");
	        
	        if(user==null){
	            logger.info("user not login");
	            hsr1.sendError(800);
	            return false;
	        }
	        else{
	        	OperateLogDTO dto = new OperateLogDTO();
	        	dto.setOperateDate(DateTool.getDateStringYMD(new Date()));
	        	dto.setOperateTime(DateTool.getDateStringHMS(new Date()));
	        	
	        	if("1".equals(user.getRoleId())){
	        		 return true;
	        	}
	        	
	        	if(o instanceof HandlerMethod){
		        	HandlerMethod d = (HandlerMethod) o;
		        	dto.setOperateMethod(d.getMethod().getName());
		        	dto.setOperateObject(d.getBeanType().getSimpleName());
		        	boolean authflag = false;
		        	List<TreeDTO> listAuthTree = roleService.findRoleAuthTree(user);
			        for(TreeDTO authdto :listAuthTree){
			        	
			        	if("queryMenu".equals(d.getMethod().getName())){
			        		authflag = true;
			        		break;
			        	}
			        	else if(d.getMethod().getName().equals(authdto.getUrl())){
			        		authflag = true;
			        		break;
			        	}
			        	continue;
			        }
		        	
			        if(authflag==false){
			        	hsr1.setCharacterEncoding("UTF-8");
			        	hsr1.setContentType("application/x-www-form-urlencoded");
			        	hsr1.getWriter().print("\"您没有获得此方法的授权,请联系管理员\"");
			        	return false;
			        }
	        	}
	        	else{
	        		dto.setOperateMethod("");
	        		dto.setOperateObject("");
	        	}
	        	dto.setUserId(user.getUserId());
	        	dto.setState("1");
	        	dto.setOrgId(user.getOrgId());
	        	operateLogService.addOperateLog(dto);
	        }
	        return true;
	    }
	 
	    @Override
	    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav) throws Exception {
	    	 UserDTO user=(UserDTO) hsr.getSession().getAttribute("currentUser");
	    	 if(user!=null){
		    	 logger.info("user  login::"+user.getUserName());
		    	 logger.info("ModelAndView info::"+mav);
	    	 }

	    	 
	    }
	 
	    @Override
	    public void afterCompletion(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, Exception excptn) throws Exception {
	    	 logger.info("Object::"+o);
	    	 logger.info("excptn::"+excptn);
	    }
	
}