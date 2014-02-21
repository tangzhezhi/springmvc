package org.tang.jpa.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.tang.jpa.dto.publicInformation.EmailDTO;
import org.tang.jpa.dto.system.ResourceDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.publicInformation.MailService;
import org.tang.jpa.service.system.ResourceService;
import org.tang.jpa.service.system.UserService;
import org.tang.jpa.utils.JsonTool;
import org.tang.jpa.utils.MyConstants;
import org.tang.jpa.utils.Page;


@Controller("userController")  
@RequestMapping("user")  
@SessionAttributes("currentUser")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private MailService mailService;
	
	
	@RequestMapping(value = "/menu", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryMenu(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="topid",required=false) String menuTopId) {  
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("userName", dto.getUserName());
        model.put("userLevel", dto.getUserLevel());
        dto.setMenuTopId(menuTopId);
        ResourceDTO rdto = new ResourceDTO();
        rdto.setRoleId(dto.getRoleId());
        List<ResourceDTO> rlist = resourceService.findUserResource(rdto);
        
        String responseStr = JsonTool.modifyStr(JsonTool.generateTree(rlist, new ResourceDTO("0","0","翼机通平台")));
        
        JSONArray json = JSONArray.fromObject(responseStr); 
        model.put("data", json);
        return model;  
    }  
	
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryUser", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryUser(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="userName",required=false) String userName
    		,@RequestParam(value="page",required=false) int pageNo
    		,@RequestParam(value="rows",required=false) int pageSize) {  
        Map<String, Object> model = new HashMap<String, Object>();
        
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map params = new HashMap();
        params.put("userName", userName);
        if(!dto.getRoleId().equals("1")){
      	  params.put("orgId", dto.getOrgId());
        }
        page.setParams(params);
        Page p = userService.findUser(page);
        model.put("rows", p.getResults());
        model.put("total", p.getTotalRecord());
        return model;  
    }  
	
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)  
    @ResponseBody  
    public String addUser(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="userid",required=false) String Userid,
				@RequestParam(value="orgid",required=false) String Orgid,
				@RequestParam(value="departmentid",required=false) String Departmentid,
				@RequestParam(value="roleid",required=false) String Roleid,
				@RequestParam(value="username",required=false) String Username,
				@RequestParam(value="userpwd",required=false) String Userpwd,
				@RequestParam(value="userphone",required=false) String Userphone,
				@RequestParam(value="useremail",required=false) String Useremail,
				@RequestParam(value="usertype",required=false) String Usertype,
				@RequestParam(value="userlevel",required=false) String Userlevel,
				@RequestParam(value="usercredits",required=false) String Usercredits
    		) {  
	        	UserDTO rdto = new UserDTO();
	        	rdto.setUserId(Userid);
	        	rdto.setOrgId(Orgid);
	        	rdto.setRoleId(Roleid);
	        	rdto.setDepartmentId(Departmentid);
	        	rdto.setUserName(Username);
	        	rdto.setUserPwd(Userpwd);
	        	rdto.setUserPhone(Userphone);
	        	rdto.setUserEmail(Useremail);
	        	rdto.setUserType(Usertype);
	        	rdto.setUserLevel(Userlevel);
	       
	        int flag =  userService.insertUser(rdto);
	        if(flag == 1){
	        	EmailDTO edto = new EmailDTO();
	        	edto.setToadd(rdto.getUserEmail());
	        	edto.setSubject("您考试汤的账号与密码");
	        	edto.setContent("您考试汤的账号是："+rdto.getUserName()+"  密码是:"+rdto.getUserPwd());
	        	try {
					mailService.sendMimeAccoutInfo(edto);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
	        	
	        	return MyConstants.ADDSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.ADDFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/modifyUser", method = RequestMethod.POST)  
    @ResponseBody  
    public String modifyUser(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="userid",required=false) String Userid,
				@RequestParam(value="orgid",required=false) String Orgid,
				@RequestParam(value="roleid",required=false) String Roleid,
				@RequestParam(value="departmentid",required=false) String Departmentid,
				@RequestParam(value="username",required=false) String Username,
				@RequestParam(value="userpwd",required=false) String Userpwd,
				@RequestParam(value="userphone",required=false) String Userphone,
				@RequestParam(value="useremail",required=false) String Useremail,
				@RequestParam(value="usertype",required=false) String Usertype,
				@RequestParam(value="userlevel",required=false) String Userlevel,
				@RequestParam(value="usercredits",required=false) String Usercredits
    		) {  
	        UserDTO rdto = new UserDTO();
	        rdto.setUserId(Userid);
        	rdto.setOrgId(Orgid);
        	rdto.setDepartmentId(Departmentid);
        	rdto.setRoleId(Roleid);
        	rdto.setUserName(Username);
        	rdto.setUserPwd(Userpwd);
        	rdto.setUserPhone(Userphone);
        	rdto.setUserEmail(Useremail);
        	rdto.setUserType(Usertype);
        	rdto.setUserLevel(Userlevel);
	       
	        int flag =  userService.updateUser(rdto);
	        if(flag == 1){
	        	return MyConstants.MODIFYSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.MODIFYFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)  
    @ResponseBody  
    public String deleteUser(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="userid",required=true) String userId) {  
	        int flag =  userService.deleteUser(userId);
	        if(flag == 1){
	        	return MyConstants.DELSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.DELFAIL.getName();
	        }
    }
}
