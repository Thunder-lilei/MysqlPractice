package cn.edu.imufe.service.impl;


import java.util.ArrayList;
import java.util.List;

import cn.edu.imufe.dao.AnswerHistoryMapper;
import cn.edu.imufe.dao.AnswerMapper;
import cn.edu.imufe.po.Answer;
import cn.edu.imufe.po.AnswerHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.imufe.pojo.AnswerHistoryPojo;
import cn.edu.imufe.service.AnswerHistoryService;
@Service
public class AnswerHistoryServiceImpl implements AnswerHistoryService {
	private final AnswerHistoryMapper answerHistoryDao;
	private final AnswerMapper answerDao;
	@Autowired
	public AnswerHistoryServiceImpl(AnswerHistoryMapper answerHistoryDao, AnswerMapper answerDao) {
		this.answerHistoryDao = answerHistoryDao;
		this.answerDao = answerDao;
	}

	@Override
	public int insert(AnswerHistory record) {
		// TODO Auto-generated method stub
		return answerHistoryDao.insert(record);
	}

	@Override
	public AnswerHistory selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return answerHistoryDao.selectByPrimaryKey(id);
	}

	@Override
	public AnswerHistory selectByUserIdAndAnswerId(Long userId, Long answerId) {
		// TODO Auto-generated method stub
		return answerHistoryDao.selectByUserIdAndAnswerId(userId,answerId);
	}

	@Override
	public int updateByPrimaryKey(AnswerHistory record) {
		// TODO Auto-generated method stub
		return answerHistoryDao.updateByPrimaryKeySelective(record);
	}

	@SuppressWarnings("null")
	@Override
	public List<AnswerHistoryPojo> selectUserAnswerHistory(Long id) {
		// TODO Auto-generated method stub
		List<AnswerHistory> answerHistoryList = answerHistoryDao.selectUserAnswerHistory(id);
		List<AnswerHistoryPojo> answerHistoryPojoList = new ArrayList<>();
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
