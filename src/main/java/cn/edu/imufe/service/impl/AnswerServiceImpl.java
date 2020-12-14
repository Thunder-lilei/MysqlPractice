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
	@Autowired
	AnswerMapper answerdao;

	@Override
	public Answer selectByPrimaryKey(Long id) {
		return answerdao.selectByPrimaryKey(id);
	}

	@Override
	public List<Long> selectAllId() {
		// TODO Auto-generated method stub
		return answerdao.selectAllId();
	}

	@Override
	public int insertSelective(Answer record) {
		// TODO Auto-generated method stub
		return answerdao.insertSelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return answerdao.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Answer record) {
		// TODO Auto-generated method stub
		return answerdao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<AnswerPojo> selectAllIdWithTitle() {
		// TODO Auto-generated method stub
		return answerdao.selectAllIdWithTitle();
	}

}
