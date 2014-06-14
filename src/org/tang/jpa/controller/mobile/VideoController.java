package org.tang.jpa.controller.mobile;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tang.jpa.service.publicInformation.VideoService;
import org.tang.jpa.utils.MobileConstant;
import org.tang.jpa.utils.Page;

@Controller("videoMobileController")  
@RequestMapping("mobile") 
public class VideoController {
	@Autowired
	private VideoService videoService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryVideo", method = {RequestMethod.POST , RequestMethod.GET})  
    @ResponseBody  
    public ModelMap queryVideo(
    		 @RequestParam(value="videoname",required=false) String videoName,
    		 @RequestParam(value="createtime",required=false) String createTime
    		,@RequestParam(value="page",required=false) String pageNo
    		,@RequestParam(value="rows",required=false) String pageSize) {  
		ModelMap mm = new ModelMap();
        
        Page page = new Page();
        try {
			page.setPageNo(Integer.valueOf(pageNo));
			page.setPageSize(Integer.valueOf(pageSize));
			Map params = new HashMap();
			params.put("videoname", videoName);
			page.setParams(params);
			Page p = videoService.findVideo(page);
			mm.put("sessionKey", "examTang");
			
			if(p!=null){
				mm.put("msgFlag", MobileConstant.video_query_success);
			    mm.put("response",p==null?0:p.getResults());
			    mm.put("total", p==null?0:p.getTotalRecord());
			}
			else{
				mm.put("msgFlag", MobileConstant.video_query_fail);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
        return mm;  
    }  

}
