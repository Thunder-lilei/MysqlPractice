package cn.edu.imufe.service.impl;

import cn.edu.imufe.dao.PaperMapper;
import cn.edu.imufe.po.Paper;
import cn.edu.imufe.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <h3>MysqlPractice</h3>
 * <p>试卷类接口实现类</p>
 *
 * @author : 李雷
 * @date : 2020-12-15 20:03
 **/
@Service
public class PaperServiceImpl implements PaperService {
    private final PaperMapper paperMapper;
    @Autowired
    public PaperServiceImpl(PaperMapper paperMapper) {
        this.paperMapper = paperMapper;
    }

    @Override
    public Integer addPaper(Paper paper) {
        if (getPaperByPaperName(paper.getPaperName()) != null) {return 0;}
        return paperMapper.insertSelective(paper);
    }

    @Override
    public Paper getPaperByPaperName(String paperName) {
        return paperMapper.selectByPaperName(paperName);
    }

    @Override
    public Paper getPaperByPaperId(Long paperId) {
        return paperMapper.selectByPrimaryKey(paperId);
    }
}
