
package org.tang.jpa.dao.exam;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tang.jpa.dto.exam.UserexamDTO;
import org.tang.jpa.dto.exam.UserexamdetailsDTO;
import org.tang.jpa.dto.system.TreeDTO;
import org.tang.jpa.dto.system.UserDTO;
import org.tang.jpa.utils.Page;

@Repository
public interface ExamDao  {
	
	public List<TreeDTO> findExamTree(UserDTO dto);

	public int insertUserExam(UserexamDTO userexamDTO);

	public int insertUserExamDetails(List<UserexamdetailsDTO> uList);

	public String findUserExamId(UserexamDTO userexamDTO);

	public int updateUserExamDetails(List<UserexamdetailsDTO> uList);

}
