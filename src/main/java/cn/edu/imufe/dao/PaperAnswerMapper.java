package cn.edu.imufe.dao;

import cn.edu.imufe.po.PaperAnswer;
import cn.edu.imufe.po.PaperAnswerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PaperAnswerMapper {
    long countByExample(PaperAnswerExample example);

    int deleteByExample(PaperAnswerExample example);

    int deleteByPrimaryKey(Long id);

    Integer deletePaperAnswerByPaperId(Long paperId);

    Integer deletePaperAnswerByPaperIdAndAnswerId(@Param("paperId") Long paperId, @Param("answerId") Long answerId);

    int insert(PaperAnswer record);

    int insertSelective(PaperAnswer record);

    List<PaperAnswer> selectByExample(PaperAnswerExample example);

    PaperAnswer selectByPrimaryKey(Long id);

    List<Long> selectAnswerIdByPaperId(Long paperId);

    PaperAnswer selectByPaperIdAndAnswerId(@Param("paperId") Long paperId, @Param("answerId") Long answerId);

    int updateByExampleSelective(@Param("record") PaperAnswer record, @Param("example") PaperAnswerExample example);

    int updateByExample(@Param("record") PaperAnswer record, @Param("example") PaperAnswerExample example);

    int updateByPrimaryKeySelective(PaperAnswer record);

    int updateByPrimaryKey(PaperAnswer record);
}