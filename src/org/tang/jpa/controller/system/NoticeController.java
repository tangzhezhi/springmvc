package org.tang.jpa.controller.system;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.tang.jpa.dto.system.NoticeDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.system.NoticeService;
import org.tang.jpa.utils.MyConstants;
import org.tang.jpa.utils.Page;

@Controller("noticeController")  
@RequestMapping("notice")  
@SessionAttributes("currentUser")
public class NoticeController {
	@Autowired
	private NoticeService noticeService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryNotice", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryNotice(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="noticename",required=false) String noticeName
    		,@RequestParam(value="page",required=false) int pageNo
    		,@RequestParam(value="rows",required=false) int pageSize) {  
        Map<String, Object> model = new HashMap<String, Object>();
        
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map params = new HashMap();
        params.put("noticename", noticeName);
        page.setParams(params);
        Page p = noticeService.findNotice(page);
        model.put("rows",p==null?0:p.getResults());
        model.put("total", p==null?0:p.getTotalRecord());
        return model;  
    }  
	
	
	@RequestMapping(value = "/addNotice", method = RequestMethod.POST)  
    @ResponseBody  
    public String addNotice(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="id",required=false) String id,
				@RequestParam(value="authorid",required=false) String authorid,
				@RequestParam(value="authorname",required=false) String authorname,
				@RequestParam(value="type",required=false) String type,
				@RequestParam(value="title",required=false) String title,
				@RequestParam(value="content",required=false) String content,
				@RequestParam(value="createtime",required=false) String createtime
    		) {  
	        	NoticeDTO rdto = new NoticeDTO();
	        	rdto.setId(id);
	        	rdto.setAuthorId(authorid);
	        	rdto.setAuthorName(authorname);
	        	rdto.setType(type);
	        	rdto.setTitle(title);
	        	rdto.setContent(content);
	        	rdto.setCreateTime(createtime);
	       
	        int flag =  noticeService.pushNoticeRealTime(rdto);
	        if(flag == 1){
	        	return MyConstants.ADDSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.ADDFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/modifyNotice", method = RequestMethod.POST)  
    @ResponseBody  
    public String modifyNotice(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="id",required=false) String id,
				@RequestParam(value="authorid",required=false) String authorid,
				@RequestParam(value="authorname",required=false) String authorname,
				@RequestParam(value="type",required=false) String type,
				@RequestParam(value="title",required=false) String title,
				@RequestParam(value="content",required=false) String content,
				@RequestParam(value="createtime",required=false) String createtime
    		) {  
	        NoticeDTO rdto = new NoticeDTO();
        		rdto.setId(id);
        		rdto.setAuthorId(authorid);
        		rdto.setAuthorName(authorname);
        		rdto.setType(type);
        		rdto.setTitle(title);
        		rdto.setContent(content);
        		rdto.setCreateTime(createtime);
	       
	        int flag =  noticeService.updateNotice(rdto);
	        if(flag == 1){
	        	return MyConstants.MODIFYSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.MODIFYFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/deleteNotice", method = RequestMethod.POST)  
    @ResponseBody  
    public String deleteNotice(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="noticeid",required=true) String noticeId) {  
			NoticeDTO rdto = new NoticeDTO();
	        int flag =  noticeService.deleteNotice(noticeId);
	        if(flag == 1){
	        	return MyConstants.DELSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.DELFAIL.getName();
	        }
    }
	
}
