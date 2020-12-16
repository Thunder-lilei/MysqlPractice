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
	public Answer selectByQuestion(String question) {
		return answerDao.selectByQuestion(question);
	}

	@Override
	public List<Long> selectAllId() {
		return answerDao.selectAllId();
	}

	/*
	 * @Author 李雷
	 * @Description
	 * 不允许重名
	 * @CreateDate 15:29 2020/12/16
	 * @UpdateDate 15:29 2020/12/16
	 * @Param [record]
	 * @return int
	 **/
	@Override
	public int insertSelective(Answer record) {
		if (selectByQuestion(record.getQuestion()) != null) {return 0;}
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
