package cn.edu.imufe.service;
import java.util.List;

import cn.edu.imufe.entity.*;
public interface AnswerService {
	Answer selectByPrimaryKey(Integer id);
	
	List<Integer> selectAllid();
	
	int insertSelective(Answer record);
}
