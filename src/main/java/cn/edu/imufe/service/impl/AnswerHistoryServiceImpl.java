package cn.edu.imufe.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.imufe.dao.AnswerDao;
import cn.edu.imufe.dao.AnswerhistoryDao;
import cn.edu.imufe.entity.Answer;
import cn.edu.imufe.entity.AnswerHistory;
import cn.edu.imufe.pojo.AnswerHistoryPojo;
import cn.edu.imufe.service.AnswerHistoryService;
@Service
public class AnswerHistoryServiceImpl implements AnswerHistoryService {
	@Autowired
	AnswerhistoryDao answerHistoryDao;
	@Autowired
	AnswerDao answerDao;

	@Override
	public int insert(AnswerHistory record) {
		// TODO Auto-generated method stub
		return answerHistoryDao.insert(record);
	}

	@Override
	public AnswerHistory selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return answerHistoryDao.selectByPrimaryKey(id);
	}

	@Override
	public AnswerHistory selectByUserIdAndAnswerId(Integer userId, Integer answerId) {
		// TODO Auto-generated method stub
		return answerHistoryDao.selectByUserIdAndAnswerId(userId,answerId);
	}

	@Override
	public int updateByPrimaryKey(AnswerHistory record) {
		// TODO Auto-generated method stub
		return answerHistoryDao.updateByPrimaryKey(record);
	}

	@SuppressWarnings("null")
	@Override
	public List<AnswerHistoryPojo> selectUserAnswerHistory(Integer id) {
		// TODO Auto-generated method stub
		List<AnswerHistory> answerHistoryList = answerHistoryDao.selectUserAnswerHistory(id);
		List<AnswerHistoryPojo> answerHistoryPojoList = new ArrayList<AnswerHistoryPojo>();
		for (AnswerHistory answerHistory : answerHistoryList) {
			AnswerHistoryPojo answerHistoryPojo = new AnswerHistoryPojo();
			Answer answer = answerDao.selectByPrimaryKey(answerHistory.getAnswerId());
			answerHistoryPojo.setAnswerId(answer.getId());
			answerHistoryPojo.setTitle(answer.getQuestion());
			answerHistoryPojo.setStatus(answerHistory.getQuestionStatus());
			answerHistoryPojoList.add(answerHistoryPojo);
		}
		return answerHistoryPojoList;
	}


}
