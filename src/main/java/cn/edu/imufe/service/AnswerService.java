package cn.edu.imufe.service;
import java.util.List;

import cn.edu.imufe.entity.*;
import cn.edu.imufe.pojo.Answer_id_title;
public interface AnswerService {
	Answer selectByPrimaryKey(Integer id);
	
	List<Integer> selectAllid();
	
	List<Answer_id_title> selectAllIdwithTitle();
	
	int insertSelective(Answer record);
	
	int deleteByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(Answer record);
}
