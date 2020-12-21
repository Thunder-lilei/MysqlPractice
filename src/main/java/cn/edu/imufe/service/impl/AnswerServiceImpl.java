package cn.edu.imufe.service.impl;

import java.util.List;

import cn.edu.imufe.dao.AnswerMapper;
import cn.edu.imufe.po.Answer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.imufe.pojo.AnswerBaseInfoPojo;
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

	@Override
	public List<AnswerBaseInfoPojo> getAllAnswerBaseInfo() {
		return answerDao.getAllAnswerBaseInfo();
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
	public Answer getAnswerByQuestionWithoutId(Answer answer) {
		return answerDao.getAnswerByQuestionWithoutId(answer);
	}

	/*
	 * @Author 李雷
	 * @Description
	 * 利用PageHelper插件实现问题基本信息分页查询
	 * @CreateDate 15:20 2020/12/21
	 * @UpdateDate 15:20 2020/12/21
	 * @Param [page, pageSize]
	 * @return com.github.pagehelper.PageInfo<?>
	 **/
	@Override
	public PageInfo<?> getAllAnswerBaseInfo(int page, int pageSize) {
		PageHelper.startPage(page,pageSize);
		List<AnswerBaseInfoPojo> answerList = getAllAnswerBaseInfo();
		return new PageInfo<>(answerList);
	}

}
