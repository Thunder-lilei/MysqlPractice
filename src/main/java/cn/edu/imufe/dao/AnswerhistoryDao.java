package cn.edu.imufe.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.edu.imufe.entity.AnswerHistory;
import cn.edu.imufe.entity.AnswerHistoryExample;

public interface AnswerhistoryDao {
    int countByExample(AnswerHistoryExample example);

    int deleteByExample(AnswerHistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AnswerHistory record);

    int insertSelective(AnswerHistory record);

    List<AnswerHistory> selectByExample(AnswerHistoryExample example);

    AnswerHistory selectByPrimaryKey(Integer id);
    
    AnswerHistory selectByUserIdAndAnswerId(@Param("userId")Integer userId, @Param("answerId")Integer answerId);
    
    List<AnswerHistory> selectUserAnswerHistory(@Param("id")Integer id);

    int updateByExampleSelective(@Param("record") AnswerHistory record, @Param("example") AnswerHistoryExample example);

    int updateByExample(@Param("record") AnswerHistory record, @Param("example") AnswerHistoryExample example);

    int updateByPrimaryKeySelective(AnswerHistory record);

    int updateByPrimaryKey(AnswerHistory record);
}