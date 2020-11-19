package cn.edu.imufe.dao;

import cn.edu.imufe.entity.Answer;
import cn.edu.imufe.entity.AnswerExample;
import cn.edu.imufe.pojo.AnswerIdTitle;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnswerDao {
    int countByExample(AnswerExample example);

    int deleteByExample(AnswerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Answer record);

    int insertSelective(Answer record);

    List<Answer> selectByExample(AnswerExample example);

    Answer selectByPrimaryKey(Integer id);
    
    List<Integer> selectAllid();
    
    List<AnswerIdTitle> selectAllIdwithTitle();
    
    int updateByExampleSelective(@Param("record") Answer record, @Param("example") AnswerExample example);

    int updateByExample(@Param("record") Answer record, @Param("example") AnswerExample example);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);
}