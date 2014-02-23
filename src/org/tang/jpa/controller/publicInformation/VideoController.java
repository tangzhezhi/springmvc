package org.tang.jpa.controller.publicInformation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.tang.jpa.dto.publicInformation.VideoDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.publicInformation.VideoService;
import org.tang.jpa.utils.DateTool;
import org.tang.jpa.utils.MyConstants;
import org.tang.jpa.utils.Page;

@Controller("videoController")  
@RequestMapping("video")  
@SessionAttributes("currentUser")
public class VideoController {
	@Autowired
	private VideoService videoService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryVideo", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryVideo(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="videoname",required=false) String videoName
    		,@RequestParam(value="page",required=false) int pageNo
    		,@RequestParam(value="rows",required=false) int pageSize) {  
        Map<String, Object> model = new HashMap<String, Object>();
        
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map params = new HashMap();
        params.put("videoname", videoName);
        page.setParams(params);
        Page p = videoService.findVideo(page);
        model.put("rows",p==null?0:p.getResults());
        model.put("total", p==null?0:p.getTotalRecord());
        return model;  
    }  
	
	
	
	@RequestMapping(value = "/queryVideoTree", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryVideoTree() {  
        Map<String, Object> model = new HashMap<String, Object>();
        try {
			List videoTree = videoService.findVideoTree();
			model.put("tree",videoTree);
		} catch (Exception e) {
			e.printStackTrace();
		}
        return model;  
    }  
	
	
	@RequestMapping(value = "/addVideo", method = RequestMethod.POST)  
    @ResponseBody  
    public String addVideo(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="videoid",required=false) String videoid,
				@RequestParam(value="videoName",required=false) String videoName,
				@RequestParam(value="videoUrl",required=false) String videoUrl,
				@RequestParam(value="userid",required=false) String userid,
				@RequestParam(value="createtime",required=false) String createtime,
				@RequestParam(value="clickNum",required=false) String clickNum,
				@RequestParam(value="videoDuration",required=false) String videoDuration,
				@RequestParam(value="videoRecommend",required=false) String videoRecommend,
				@RequestParam(value="videoSubject",required=false) String videoSubject,
				@RequestParam(value="videoTime",required=false) String videoTime
    		) {  
	        	VideoDTO rdto = new VideoDTO();
	        	rdto.setVideoid(UUID.randomUUID().toString());
	        	rdto.setVideoName(videoName);
	        	rdto.setVideoUrl(videoUrl);
	        	rdto.setUserid(dto.getUserId());
	        	rdto.setCreatetime(DateTool.getDateStringYMDHMS(new Date()));
	        	rdto.setClickNum(clickNum);
	        	rdto.setVideoDuration(videoDuration);
	        	rdto.setVideoRecommend(videoRecommend);
	        	rdto.setVideoSubject(videoSubject);
	        	rdto.setVideoTime(videoTime);
	       
	        int flag =  videoService.insertVideo(rdto);
	        if(flag == 1){
	        	return MyConstants.ADDSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.ADDFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/modifyVideo", method = RequestMethod.POST)  
    @ResponseBody  
    public String modifyVideo(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="videoid",required=false) String videoid,
				@RequestParam(value="videoName",required=false) String videoName,
				@RequestParam(value="videoUrl",required=false) String videoUrl,
				@RequestParam(value="userid",required=false) String userid,
				@RequestParam(value="createtime",required=false) String createtime,
				@RequestParam(value="clickNum",required=false) String clickNum,
				@RequestParam(value="videoDuration",required=false) String videoDuration,
				@RequestParam(value="videoRecommend",required=false) String videoRecommend,
				@RequestParam(value="videoSubject",required=false) String videoSubject,
				@RequestParam(value="videoTime",required=false) String videoTime
    		) {  
	        VideoDTO rdto = new VideoDTO();
        		rdto.setVideoid(videoid);
        		rdto.setVideoName(videoName);
        		rdto.setVideoUrl(videoUrl);
        		rdto.setUserid(dto.getUserId());
        		rdto.setClickNum(clickNum);
        		rdto.setVideoDuration(videoDuration);
        		rdto.setVideoRecommend(videoRecommend);
        		rdto.setVideoSubject(videoSubject);
	        	rdto.setVideoTime(videoTime);
	       
	        int flag =  videoService.updateVideo(rdto);
	        if(flag == 1){
	        	return MyConstants.MODIFYSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.MODIFYFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/deleteVideo", method = RequestMethod.POST)  
    @ResponseBody  
    public String deleteVideo(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="videoid",required=true) String videoId) {  
			VideoDTO rdto = new VideoDTO();
	        int flag =  videoService.deleteVideo(videoId);
	        if(flag == 1){
	        	return MyConstants.DELSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.DELFAIL.getName();
	        }
    }
	
}
