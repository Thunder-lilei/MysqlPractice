package cn.edu.imufe.service;

import java.util.List;

import cn.edu.imufe.entity.*;
import cn.edu.imufe.pojo.AnswerHistoryPojo;
public interface AnswerHistoryService {
	int insert(AnswerHistory record);
	
	AnswerHistory selectByPrimaryKey(Integer id);
	
	AnswerHistory selectByUserIdAndAnswerId(Integer UserId, Integer AnswerId);
	
	int updateByPrimaryKey(AnswerHistory record);
	
	List<AnswerHistoryPojo> selectUserAnswerHistory(Integer id);
}
