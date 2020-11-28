package cn.edu.imufe.service;

import java.util.List;

import cn.edu.imufe.entity.*;
import cn.edu.imufe.pojo.AnswerHistoryPojo;
public interface AnswerHistoryService {
	int insert(Answerhistory record);
	
	Answerhistory selectByPrimaryKey(Integer id);
	
	Answerhistory selectByUserIdAndAnswerId(Integer UserId,Integer AnswerId);
	
	int updateByPrimaryKey(Answerhistory record);
	
	List<AnswerHistoryPojo> selectUserAnswerHistory(Integer id);
}
