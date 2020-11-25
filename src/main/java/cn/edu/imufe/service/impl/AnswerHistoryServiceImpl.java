package cn.edu.imufe.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.imufe.dao.AnswerhistoryDao;
import cn.edu.imufe.entity.Answerhistory;
import cn.edu.imufe.service.AnswerHistoryService;
@Service
public class AnswerHistoryServiceImpl implements AnswerHistoryService {
	@Autowired
	AnswerhistoryDao answerHistoryDao;

	@Override
	public int insert(Answerhistory record) {
		// TODO Auto-generated method stub
		return answerHistoryDao.insert(record);
	}

	@Override
	public Answerhistory selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return answerHistoryDao.selectByPrimaryKey(id);
	}

	@Override
	public Answerhistory selectByUserIdAndAnswerId(Integer userId,Integer answerId) {
		// TODO Auto-generated method stub
		return answerHistoryDao.selectByUserIdAndAnswerId(userId,answerId);
	}

	@Override
	public int updateByPrimaryKey(Answerhistory record) {
		// TODO Auto-generated method stub
		return answerHistoryDao.updateByPrimaryKey(record);
	}


}
