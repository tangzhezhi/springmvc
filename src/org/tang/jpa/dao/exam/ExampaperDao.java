
package org.tang.jpa.dao.exam;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.exam.ExamPaperDetailsDTO;
import org.tang.jpa.dto.exam.ExampaperDTO;
import org.tang.jpa.dto.exam.OptionsDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface ExampaperDao  {
	
    public int insertExampaper(ExampaperDTO dto);  
    
    public int updateExampaper(ExampaperDTO dto);  
     
    public int deleteExampaper(String exampaperId);  
     
    public Page<?> selectExampaperAll(Page<?> page);

	public int insertOptionsToExam(ExamPaperDetailsDTO rdto);

	public int queryExamExistsOption(ExamPaperDetailsDTO rdto);

	public Page<?> queryOptionsOfExam(Page<?> page);

	public int deleteOneOptionsOfExampaper(ExamPaperDetailsDTO rdto);

	public int modifyOneOptionsOfExampaper(ExamPaperDetailsDTO rdto);

	public List<OptionsDTO> previewExampaper(ExampaperDTO rdto);

	public ExampaperDTO findExamInfo(ExamPaperDetailsDTO rdto);

	public ExampaperDTO findExamInfoByExamId(String examid);  
	
	
	public List showExamInformationTopFive(String examType);

	public Page showExamInformationAllPage(Page page);  
    
}
