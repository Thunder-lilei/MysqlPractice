package cn.edu.imufe.service.impl;

import java.util.List;

import cn.edu.imufe.dao.AnswerMapper;
import cn.edu.imufe.po.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.imufe.pojo.AnswerPojo;
import cn.edu.imufe.service.AnswerService;
@Service
public class AnswerServiceImpl implements AnswerService {
	private final AnswerMapper answerDao;
	@Autowired
	public AnswerServiceImpl(AnswerMapper answerDao) {
		this.answerDao = answerDao;
	}

	@Override
	public Answer selectByPrimaryKey(Long id) {
		return answerDao.selectByPrimaryKey(id);
	}

	@Override
	public List<Long> selectAllId() {
		return answerDao.selectAllId();
	}

	@Override
	public int insertSelective(Answer record) {
		return answerDao.insertSelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		return answerDao.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Answer record) {
		if (getAnswerByQuestionWithoutId(record) != null) {return 0;}
		return answerDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<AnswerPojo> selectAllIdWithTitle() {
		return answerDao.selectAllIdWithTitle();
	}

	@Override
	public Answer getAnswerByQuestionWithoutId(Answer answer) {
		return answerDao.getAnswerByQuestionWithoutId(answer);
	}

}
