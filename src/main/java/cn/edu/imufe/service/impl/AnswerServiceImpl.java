package cn.edu.imufe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.imufe.dao.AnswerDao;
import cn.edu.imufe.entity.Answer;
import cn.edu.imufe.service.AnswerService;
@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	AnswerDao answerdao;
	@Override
	public List<Answer> RandomProblems() {
		return null;
	}

	@Override
	public List<Answer> SelectProblems() {
		return null;
	}

	@Override
	public Answer selectByPrimaryKey(Integer id) {
		return answerdao.selectByPrimaryKey(id);
	}

	@Override
	public List<Integer> selectAllid() {
		// TODO Auto-generated method stub
		return answerdao.selectAllid();
	}

}
