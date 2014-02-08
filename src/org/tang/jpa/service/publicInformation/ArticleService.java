/*
 * Powered By tangzezhi
 * Since 2013 - 2014
 */

package org.tang.jpa.service.publicInformation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.tang.jpa.dao.publicInformation.ArticleDao;
import org.tang.jpa.dto.publicInformation.ArticleDTO;
import org.tang.jpa.utils.Page;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
	
	/**
	 * 分页
	 * @param udto
	 * @return
	 */
	public  Page findArticle(Page page){
		Page  pageList = (Page) articleDao.selectArticleAll(page);
		if(pageList!=null && pageList.getResults().size() > 0 ){
			pageList.setPageNo(page.getPageNo());
			pageList.setPageSize(page.getPageSize());
			pageList.setTotalPage(page.getTotalPage());
			pageList.setTotalRecord(page.getTotalRecord());
		}
		return pageList;
	}
	
	public int insertArticle(ArticleDTO dto){
		int flag = 0;
		if(dto!=null){
			flag = articleDao.insertArticle(dto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	public int updateArticle(ArticleDTO rdto){
		int flag = 0;
		if(rdto!=null){
			flag = articleDao.updateArticle(rdto);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
	public int deleteArticle(String articleId){
		int flag = 0;
		if(articleId!=null){
			flag = articleDao.deleteArticle(articleId);
		}
		else{
			flag = 0;
		}
		return flag;
	}
	
	
	
}
