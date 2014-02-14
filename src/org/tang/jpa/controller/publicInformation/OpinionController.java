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
import org.tang.jpa.dto.publicInformation.OpinionDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.publicInformation.OpinionService;
import org.tang.jpa.utils.DateTool;
import org.tang.jpa.utils.MyConstants;
import org.tang.jpa.utils.Page;

@Controller("opinionController")  
@RequestMapping("opinion")  
@SessionAttributes("currentUser")
public class OpinionController {
	@Autowired
	private OpinionService opinionService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryOpinion", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryOpinion(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="opinionTitle",required=false) String opinionTitle
    		,@RequestParam(value="page",required=false) int pageNo
    		,@RequestParam(value="rows",required=false) int pageSize) {  
        Map<String, Object> model = new HashMap<String, Object>();
        
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map params = new HashMap();
        params.put("opinionTitle", opinionTitle);
        page.setParams(params);
        Page p = opinionService.findOpinion(page);
        model.put("rows",p==null?0:p.getResults());
        model.put("total", p==null?0:p.getTotalRecord());
        return model;  
    }  
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryWaiteApproveOpinion", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryWaiteApproveOpinion(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="opinionTitle",required=false) String opinionTitle
    		,@RequestParam(value="page",required=false) int pageNo
    		,@RequestParam(value="rows",required=false) int pageSize) {  
        Map<String, Object> model = new HashMap<String, Object>();
        
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map params = new HashMap();
        params.put("opinionTitle", opinionTitle);
        params.put("approveState", "0");
        page.setParams(params);
        Page p = opinionService.findOpinion(page);
        model.put("rows",p==null?0:p.getResults());
        model.put("total", p==null?0:p.getTotalRecord());
        return model;  
    }  
	
	
	
	
	@RequestMapping(value = "/addOpinion", method = RequestMethod.POST)  
    @ResponseBody  
    public String addOpinion(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="opinionid",required=false) String Opinionid,
				@RequestParam(value="opinionTitle",required=false) String OpinionTitle,
				@RequestParam(value="userid",required=false) String Userid,
				@RequestParam(value="createtime",required=false) String Createtime,
				@RequestParam(value="approveState",required=false) String ApproveState,
				@RequestParam(value="opinionContent",required=false) String OpinionContent,
				@RequestParam(value="approveUserid",required=false) String ApproveUserid,
				@RequestParam(value="approveTime",required=false) String ApproveTime
    		) {  
	        	OpinionDTO rdto = new OpinionDTO();
	        	rdto.setOpinionid(UUID.randomUUID().toString());
	        	rdto.setOpinionTitle(OpinionTitle);
	        	rdto.setUserid(dto.getUserId());
	        	rdto.setCreatetime(DateTool.getDateStringYMDHMS(new Date()));
	        	rdto.setApproveState("0");
	        	rdto.setOpinionContent(OpinionContent);
	        	rdto.setApproveUserid(ApproveUserid);
	        	rdto.setApproveTime(ApproveTime);
	       
	        int flag =  opinionService.insertOpinion(rdto);
	        if(flag == 1){
	        	return MyConstants.ADDSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.ADDFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/modifyOpinion", method = RequestMethod.POST)  
    @ResponseBody  
    public String modifyOpinion(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="opinionid",required=false) String Opinionid,
				@RequestParam(value="opinionTitle",required=false) String OpinionTitle,
				@RequestParam(value="userid",required=false) String Userid,
				@RequestParam(value="createtime",required=false) String Createtime,
				@RequestParam(value="approveState",required=false) String ApproveState,
				@RequestParam(value="opinionContent",required=false) String OpinionContent,
				@RequestParam(value="approveUserid",required=false) String ApproveUserid,
				@RequestParam(value="approveTime",required=false) String ApproveTime
    		) {  
		 	int flag  = 0 ;
	        OpinionDTO rdto = new OpinionDTO();
        		rdto.setOpinionid(Opinionid);
        		rdto.setOpinionTitle(OpinionTitle);
        		rdto.setApproveState(ApproveState);
        		rdto.setOpinionContent(OpinionContent);
        		rdto.setApproveUserid(ApproveUserid);
        		rdto.setApproveTime(ApproveTime);
	       
        	OpinionDTO opiniondto = opinionService.selectOpinion(Opinionid);
        	if(opiniondto!=null && ("0").equals(opiniondto.getApproveState())){
        		flag =  opinionService.updateOpinion(rdto);
    	        if(flag == 1){
    	        	return MyConstants.MODIFYSUCCESS.getName();
    	        }
    	        else{
    	        	return MyConstants.MODIFYFAIL.getName();
    	        }
        	}
        	else{
        		return MyConstants.HADAPPROVAL.getName();
        	}

    }
	
	
	
	@RequestMapping(value = "/deleteOpinion", method = RequestMethod.POST)  
    @ResponseBody  
    public String deleteOpinion(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="opinionid",required=true) String opinionId) {  
			OpinionDTO rdto = new OpinionDTO();
			int flag = 0;
        	OpinionDTO opiniondto = opinionService.selectOpinion(opinionId);
        	if(opiniondto!=null && ("0").equals(opiniondto.getApproveState())){
        		flag =  opinionService.deleteOpinion(opinionId);
    	        if(flag == 1){
    	        	return MyConstants.DELSUCCESS.getName();
    	        }
    	        else{
    	        	return MyConstants.DELFAIL.getName();
    	        }
        	}
        	else{
        		return MyConstants.HADAPPROVAL.getName();
        	}
    }
	
	
	
	@RequestMapping(value = "/approveOpinion", method = RequestMethod.POST)  
    @ResponseBody  
    public String approveOpinion(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="opinionid",required=false) String Opinionid,
				@RequestParam(value="approveState",required=false) String ApproveState
    		) {  
		 		int flag  = 0 ;
		 		OpinionDTO rdto = new OpinionDTO();
        		rdto.setOpinionid(Opinionid);
        		rdto.setApproveState(ApproveState);
        		rdto.setApproveTime(DateTool.getDateStringYMDHMS(new Date()));
        		rdto.setApproveUserid(dto.getUserId());
        		
        		flag =  opinionService.updateApproveOpinion(rdto);
    	        if(flag == 1){
    	        	return MyConstants.SUCCESS.getName();
    	        }
    	        else{
    	        	return MyConstants.FAIL.getName();
    	        }

    }
	
	
	
}
