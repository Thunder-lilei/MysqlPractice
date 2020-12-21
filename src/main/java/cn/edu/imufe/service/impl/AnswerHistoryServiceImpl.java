package cn.edu.imufe.service.impl;


import java.util.ArrayList;
import java.util.List;

import cn.edu.imufe.dao.AnswerHistoryMapper;
import cn.edu.imufe.dao.AnswerMapper;
import cn.edu.imufe.po.Answer;
import cn.edu.imufe.po.AnswerHistory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.imufe.pojo.UserAnswerHistoryPojo;
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

	@Override
	public List<UserAnswerHistoryPojo> selectUserAnswerHistory(Long id) {
		List<AnswerHistory> answerHistoryList = answerHistoryDao.selectUserAnswerHistory(id);
		List<UserAnswerHistoryPojo> userAnswerHistoryPojoList = new ArrayList<>();
		for (AnswerHistory answerHistory : answerHistoryList) {
			UserAnswerHistoryPojo userAnswerHistoryPojo = new UserAnswerHistoryPojo();
			Answer answer = answerDao.selectByPrimaryKey(answerHistory.getAnswerId());
			userAnswerHistoryPojo.setAnswerId(answer.getId());
			userAnswerHistoryPojo.setTitle(answer.getQuestion());
			userAnswerHistoryPojo.setStatus(answerHistory.getQuestionStatus());
			userAnswerHistoryPojoList.add(userAnswerHistoryPojo);
		}
		return userAnswerHistoryPojoList;
	}

	/*
	 * @Author 李雷
	 * @Description
	 * 分页查询用户答题历史
	 * @CreateDate 16:07 2020/12/21
	 * @UpdateDate 16:07 2020/12/21
	 * @Param [id, page, pageSize]
	 * @return com.github.pagehelper.PageInfo<?>
	 **/
	@Override
	public PageInfo<?> selectUserAnswerHistoryByPage(Long id,int page, int pageSize) {
		PageHelper.startPage(page,pageSize);
		List<UserAnswerHistoryPojo> userAnswerHistoryPojos = selectUserAnswerHistory(id);
		return new PageInfo<>(userAnswerHistoryPojos);
	}


}
