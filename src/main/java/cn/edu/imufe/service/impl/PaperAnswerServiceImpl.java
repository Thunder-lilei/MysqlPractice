package cn.edu.imufe.service.impl;

import cn.edu.imufe.dao.PaperAnswerMapper;
import cn.edu.imufe.po.PaperAnswer;
import cn.edu.imufe.service.PaperAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>MysqlPractice</h3>
 * <p>试卷 题 中间表 接口实现类</p>
 *
 * @author : 李雷
 * @date : 2020-12-15 20:19
 **/
@Service
public class PaperAnswerServiceImpl implements PaperAnswerService {
    private final PaperAnswerMapper paperAnswerMapper;
    @Autowired
    public PaperAnswerServiceImpl(PaperAnswerMapper paperAnswerMapper) {
        this.paperAnswerMapper = paperAnswerMapper;
    }

    /*
     * @Author 李雷
     * @Description
     * 试卷添加试题
     * 不能重复添加
     * @CreateDate 10:35 2020/12/16
     * @UpdateDate 10:35 2020/12/16
     * @Param [paperId, answerId]
     * @return java.lang.Integer
     **/
    @Override
    public Integer addPaperAnswer(Long paperId, Long answerId) {
        if (getResultByPaperIdAndAnswerId(paperId,answerId)) {return 0;}
        PaperAnswer paperAnswer = new PaperAnswer();
        paperAnswer.setPaperId(paperId);
        paperAnswer.setAnswerId(answerId);
        return paperAnswerMapper.insertSelective(paperAnswer);
    }

    @Override
    public List<Long> getAnswerIdByPaperId(Long paperId) {
        return paperAnswerMapper.selectAnswerIdByPaperId(paperId);
    }

    @Override
    public Boolean getResultByPaperIdAndAnswerId(Long paperId, Long answerId) {
        if (paperAnswerMapper.selectByPaperIdAndAnswerId(paperId,answerId) != null) {return true;}
        return false;
    }

    @Override
    public Integer deletePaperAnswerByPaperId(Long paperId) {
        return paperAnswerMapper.deletePaperAnswerByPaperId(paperId);
    }

    @Override
    public Integer deletePaperAnswerByPaperIdAndAnswerId(Long paperId, Long answerId) {
        return paperAnswerMapper.deletePaperAnswerByPaperIdAndAnswerId(paperId,answerId);
    }
}
