package cn.edu.imufe.service;
import java.util.List;

import cn.edu.imufe.entity.*;
import cn.edu.imufe.pojo.AnswerPojo;
public interface AnswerService {
	Answer selectByPrimaryKey(Integer id);
	
	List<Integer> selectAllid();
	
	List<AnswerPojo> selectAllIdwithTitle();
	
	int insertSelective(Answer record);
	
	int deleteByPrimaryKey(Integer id);
	
	int updateByPrimaryKeySelective(Answer record);
}
