package cn.edu.imufe.service;
import java.util.List;

import cn.edu.imufe.po.*;
import cn.edu.imufe.pojo.AnswerPojo;
import com.github.pagehelper.PageInfo;

public interface AnswerService {
	Answer selectByPrimaryKey(Long id);

	Answer selectByQuestion(String question);

	List<Long> selectAllId();

	List<AnswerPojo> getAllAnswerBaseInfo();

	Answer getAnswerByQuestionWithoutId(Answer answer);

	PageInfo<?> getAllAnswerBaseInfo(int page, int pageSize);

	int insertSelective(Answer record);

	int deleteByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Answer record);
}
