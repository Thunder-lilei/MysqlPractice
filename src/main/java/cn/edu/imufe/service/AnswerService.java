package cn.edu.imufe.service;
import java.util.List;

import cn.edu.imufe.po.*;
import cn.edu.imufe.pojo.AnswerPojo;
public interface AnswerService {
	Answer selectByPrimaryKey(Long id);

	Answer selectByQuestion(String question);
	
	List<Long> selectAllId();
	
	List<AnswerPojo> selectAllIdWithTitle();

	Answer getAnswerByQuestionWithoutId(Answer answer);
	
	int insertSelective(Answer record);
	
	int deleteByPrimaryKey(Long id);
	
	int updateByPrimaryKeySelective(Answer record);
}
