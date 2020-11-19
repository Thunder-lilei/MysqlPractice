package cn.edu.imufe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.imufe.dao.AnswerhistoryDao;
import cn.edu.imufe.entity.Answer;
import cn.edu.imufe.entity.Answerhistory;
import cn.edu.imufe.service.AnswerHistoryService;
import cn.edu.imufe.service.AnswerService;
@Service
public class AnswerHistoryServiceImpl implements AnswerHistoryService {
	@Autowired
	AnswerhistoryDao answerhistorydao;

	@Override
	public int insert(Answerhistory record) {
		// TODO Auto-generated method stub
		return answerhistorydao.insert(record);
	}


}
