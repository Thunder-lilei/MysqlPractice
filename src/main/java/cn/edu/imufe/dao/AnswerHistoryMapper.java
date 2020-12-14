package cn.edu.imufe.dao;

import cn.edu.imufe.po.AnswerHistory;
import cn.edu.imufe.po.AnswerHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnswerHistoryMapper {
    long countByExample(AnswerHistoryExample example);

    int deleteByExample(AnswerHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AnswerHistory record);

    int insertSelective(AnswerHistory record);

    List<AnswerHistory> selectByExample(AnswerHistoryExample example);

    AnswerHistory selectByPrimaryKey(Long id);

    AnswerHistory selectByUserIdAndAnswerId(@Param("userId")Long userId, @Param("answerId")Long answerId);

    List<AnswerHistory> selectUserAnswerHistory(@Param("id")Long id);

    int updateByExampleSelective(@Param("record") AnswerHistory record, @Param("example") AnswerHistoryExample example);

    int updateByExample(@Param("record") AnswerHistory record, @Param("example") AnswerHistoryExample example);

    int updateByPrimaryKeySelective(AnswerHistory record);

    int updateByPrimaryKey(AnswerHistory record);
}