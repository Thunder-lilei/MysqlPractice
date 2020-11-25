package cn.edu.imufe.service;

import cn.edu.imufe.entity.*;
public interface AnswerHistoryService {
	int insert(Answerhistory record);
	
	Answerhistory selectByPrimaryKey(Integer id);
	
	Answerhistory selectByUserIdAndAnswerId(Integer UserId,Integer AnswerId);
	
	int updateByPrimaryKey(Answerhistory record);
}
