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
import org.tang.jpa.dto.publicInformation.ArticleDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.publicInformation.ArticleService;
import org.tang.jpa.utils.DateTool;
import org.tang.jpa.utils.MyConstants;
import org.tang.jpa.utils.Page;

@Controller("articleController")  
@RequestMapping("article")  
@SessionAttributes("currentUser")
public class ArticleController {
	@Autowired
	private ArticleService articleService;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryArticle", method = RequestMethod.POST)  
    @ResponseBody  
    public Map<String, Object> queryArticle(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="articlename",required=false) String articleName
    		,@RequestParam(value="page",required=false) int pageNo
    		,@RequestParam(value="rows",required=false) int pageSize) {  
        Map<String, Object> model = new HashMap<String, Object>();
        
        Page page = new Page();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map params = new HashMap();
        params.put("articlename", articleName);
        page.setParams(params);
        Page p = articleService.findArticle(page);
        model.put("rows",p==null?0:p.getResults());
        model.put("total", p==null?0:p.getTotalRecord());
        return model;  
    }  
	
	
	@RequestMapping(value = "/addArticle", method = RequestMethod.POST)  
    @ResponseBody  
    public String addArticle(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="articleId",required=false) String articleId,
				@RequestParam(value="articleContent",required=false) String articleContent,
				@RequestParam(value="articleType",required=false) String articleType,
				@RequestParam(value="articleOperater",required=false) String articleOperater,
				@RequestParam(value="createTime",required=false) String createTime,
				@RequestParam(value="articleTitle",required=false) String articleTitle
    		) {  
	        ArticleDTO rdto = new ArticleDTO();
	        	rdto.setArticleId(UUID.randomUUID().toString());
	        	rdto.setArticleContent(articleContent);
	        	rdto.setArticleType(articleType);
	        	rdto.setArticleOperater(dto.getUserName());
	        	rdto.setCreateTime(DateTool.getDateStringYMDHMS(new Date()));
	        	rdto.setArticleTitle(articleTitle);
	       
	        int flag =  articleService.insertArticle(rdto);
	        if(flag == 1){
	        	return MyConstants.ADDSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.ADDFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/modifyArticle", method = RequestMethod.POST)  
    @ResponseBody  
    public String modifyArticle(@ModelAttribute("currentUser") UserDTO dto,
				@RequestParam(value="articleId",required=false) String articleId,
				@RequestParam(value="articleContent",required=false) String articleContent,
				@RequestParam(value="articleType",required=false) String articleType,
				@RequestParam(value="articleOperater",required=false) String articleOperater,
				@RequestParam(value="createTime",required=false) String createTime,
				@RequestParam(value="articleTitle",required=false) String articleTitle
    		) {  
	        ArticleDTO rdto = new ArticleDTO();
        		rdto.setArticleId(articleId);
        		rdto.setArticleContent(articleContent);
        		rdto.setArticleType(articleType);
        		rdto.setArticleOperater(dto.getUserName());
        		rdto.setCreateTime(createTime);
        		rdto.setArticleTitle(articleTitle);
	       
	        int flag =  articleService.updateArticle(rdto);
	        if(flag == 1){
	        	return MyConstants.MODIFYSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.MODIFYFAIL.getName();
	        }
    }
	
	
	
	@RequestMapping(value = "/deleteArticle", method = RequestMethod.POST)  
    @ResponseBody  
    public String deleteArticle(@ModelAttribute("currentUser") UserDTO dto
    		,@RequestParam(value="articleid",required=true) String articleId) {  
			ArticleDTO rdto = new ArticleDTO();
	        int flag =  articleService.deleteArticle(articleId);
	        if(flag == 1){
	        	return MyConstants.DELSUCCESS.getName();
	        }
	        else{
	        	return MyConstants.DELFAIL.getName();
	        }
    }
	
}
