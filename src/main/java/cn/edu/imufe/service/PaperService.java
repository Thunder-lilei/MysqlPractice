package cn.edu.imufe.service;

import cn.edu.imufe.po.Paper;

import java.util.List;

/**
 * <h3>MysqlPractice</h3>
 * <p>试卷类接口</p>
 *
 * @author : 李雷
 * @date : 2020-12-15 20:03
 **/
public interface PaperService {
    Integer addPaper(Paper paper);

    Paper getPaperByPaperName(String paperName);

    Paper getPaperByPaperNameWithoutId(Paper paper);

    Paper getPaperByPaperId(Long paperId);

    List<Paper> getAllPaper();

    Integer deletePaperById(Long Id);

    Integer updatePaper(Paper paper);
}
