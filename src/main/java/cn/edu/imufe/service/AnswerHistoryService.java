package cn.edu.imufe.service;

import java.util.List;

import cn.edu.imufe.po.*;
import cn.edu.imufe.pojo.UserAnswerHistoryPojo;
import com.github.pagehelper.PageInfo;

public interface AnswerHistoryService {
	int insert(AnswerHistory record);

	AnswerHistory selectByPrimaryKey(Long id);

	AnswerHistory selectByUserIdAndAnswerId(Long UserId, Long AnswerId);

	int updateByPrimaryKey(AnswerHistory record);

	List<UserAnswerHistoryPojo> selectUserAnswerHistory(Long id);

	PageInfo<?> selectUserAnswerHistoryByPage(Long id,int page, int pageSize);
}
