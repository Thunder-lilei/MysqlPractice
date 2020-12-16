package cn.edu.imufe.service;

import cn.edu.imufe.po.PaperAnswer;

import java.util.List;

/**
 * <h3>MysqlPractice</h3>
 * <p>试卷 题 中间表 接口</p>
 *
 * @author : 李雷
 * @date : 2020-12-15 20:18
 **/
public interface PaperAnswerService {
    Integer addPaperAnswer(PaperAnswer paperAnswer);

    List<Long> getAnswerIdByPaperId(Long paperId);

    Integer deletePaperAnswerByPaperId(Long paperId);
}
