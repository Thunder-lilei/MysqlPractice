package cn.edu.imufe.service;

import java.util.List;

import cn.edu.imufe.po.*;
import cn.edu.imufe.pojo.AnswerHistoryPojo;
public interface AnswerHistoryService {
	int insert(AnswerHistory record);
	
	AnswerHistory selectByPrimaryKey(Long id);
	
	AnswerHistory selectByUserIdAndAnswerId(Long UserId, Long AnswerId);
	
	int updateByPrimaryKey(AnswerHistory record);
	
	List<AnswerHistoryPojo> selectUserAnswerHistory(Long id);
}
