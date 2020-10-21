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
	public Answer selectByPrimaryKey(Integer id) {
		return answerdao.selectByPrimaryKey(id);
	}

	@Override
	public List<Integer> selectAllid() {
		// TODO Auto-generated method stub
		return answerdao.selectAllid();
	}

	@Override
	public int insertSelective(Answer record) {
		// TODO Auto-generated method stub
		return answerdao.insertSelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return answerdao.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Answer record) {
		// TODO Auto-generated method stub
		return answerdao.updateByPrimaryKey(record);
	}

}
