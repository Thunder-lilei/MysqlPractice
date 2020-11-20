 package cn.edu.imufe.dao;

import cn.edu.imufe.entity.Answerhistory;
import cn.edu.imufe.entity.AnswerhistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnswerhistoryDao {
    int countByExample(AnswerhistoryExample example);

    int deleteByExample(AnswerhistoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Answerhistory record);

    int insertSelective(Answerhistory record);

    List<Answerhistory> selectByExample(AnswerhistoryExample example);

    Answerhistory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Answerhistory record, @Param("example") AnswerhistoryExample example);

    int updateByExample(@Param("record") Answerhistory record, @Param("example") AnswerhistoryExample example);

    int updateByPrimaryKeySelective(Answerhistory record);

    int updateByPrimaryKey(Answerhistory record);
}