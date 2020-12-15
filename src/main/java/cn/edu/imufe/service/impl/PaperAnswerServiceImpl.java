package cn.edu.imufe.service.impl;

import cn.edu.imufe.dao.PaperAnswerMapper;
import cn.edu.imufe.po.PaperAnswer;
import cn.edu.imufe.service.PaperAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Integer addPaperAnswer(PaperAnswer paperAnswer) {
        return paperAnswerMapper.insertSelective(paperAnswer);
    }
}
