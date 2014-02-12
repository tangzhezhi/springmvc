
package org.tang.jpa.dao.publicInformation;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.publicInformation.ArticleDTO;
import org.tang.jpa.dto.publicInformation.PictureDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface ArticleDao  {
	
    public int insertArticle(ArticleDTO dto);  
    
    public int insertRoleArticle(ArticleDTO dto);  
    
    public int updateArticle(ArticleDTO dto);  
     
    public int deleteArticle(String articleId);  
     
    public Page<?> selectArticleAll(Page<?> page);

	public List showArticleInformationTopFive(String articleType);

	public List<ArticleDTO> previewArticle(ArticleDTO rdto);

	public int insertPics(List<PictureDTO> picdtoList);

	public List showPicInformationTopFive();  
    
}
