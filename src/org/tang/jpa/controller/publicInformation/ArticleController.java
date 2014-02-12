package org.tang.jpa.controller.publicInformation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.tang.jpa.dto.publicInformation.ArticleDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.service.publicInformation.ArticleService;
import org.tang.jpa.utils.DateTool;
import org.tang.jpa.utils.MyConstants;
import org.tang.jpa.utils.Page;
import org.tang.jpa.utils.UploadFile;

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
	        	
	        	List<String> articlePics = getImageUrl(articleContent);
	        	rdto.setArticlePics(articlePics);
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
	
	
	
	
	
	  /**
     * 公用上传Action UploadFile.name 文件名,从前台传人 UploadFile.filePath 路径,从前台传人
     * ad为存放广告的文件夹 news为存放新闻的文件夹 上传之后的路径地址为
     * /upload/{UploadFile.filePath}/今年(2012)/当月(02)/UploadFile.name
     *
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/ckeditorUpload")
    @ResponseBody
    public String processImageUpload(UploadFile file,HttpServletRequest request) {
        String callback = request.getParameter("CKEditorFuncNum");
        try {
            File uploadFile;
            if (StringUtils.isBlank(file.getName())) {
                file.setName(DateTool.getDateStringYMDHMS(new Date()) );
            }
            
            String path =  "/resources/upload_pic/news";
            String filePath = request.getSession().getServletContext().getRealPath(path);
            uploadFile = new File(filePath+"/"+file.getUpload().getOriginalFilename());
            
            file.getUpload().getFileItem().write(uploadFile);
            if(StringUtils.isNotBlank(callback)){
                return "<script type='text/javascript'>"
                + "window.parent.CKEDITOR.tools.callFunction(" + callback
                + ",'" +request.getContextPath() + path+"/"+file.getUpload().getOriginalFilename() + "',''" + ")"+"</script>";
            }else{
                return "{" + file.getUpload().getOriginalFilename() + "}!Success!";
            }
 
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
 
    }
    
    
    
    
    private List<String> getImageUrl(String content){
		 Matcher matcher = Pattern.compile("<img.*src=(.*?)[^>]*?/>").matcher(content);  
	        List<String> listImgUrl = new ArrayList<String>();  
	        while (matcher.find()) {  
	            listImgUrl.add(matcher.group());  
	        }  
	        return listImgUrl;
	        
    }
    
	
}
