package cn.edu.imufe.dao;

import cn.edu.imufe.po.Answer;
import cn.edu.imufe.po.AnswerExample;
import java.util.List;

import cn.edu.imufe.pojo.AnswerPojo;
import org.apache.ibatis.annotations.Param;

public interface AnswerMapper {
    long countByExample(AnswerExample example);

    int deleteByExample(AnswerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Answer record);

    int insertSelective(Answer record);

    List<Answer> selectByExample(AnswerExample example);

    Answer selectByPrimaryKey(Long id);

    List<Long> selectAllId();

    List<AnswerPojo> selectAllIdWithTitle();

    Answer getAnswerByQuestionWithoutId(Answer answer);

    int updateByExampleSelective(@Param("record") Answer record, @Param("example") AnswerExample example);

    int updateByExample(@Param("record") Answer record, @Param("example") AnswerExample example);

    int updateByPrimaryKeySelective(Answer record);

    int updateByPrimaryKey(Answer record);
}