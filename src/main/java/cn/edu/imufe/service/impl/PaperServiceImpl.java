package cn.edu.imufe.service.impl;

import cn.edu.imufe.dao.PaperMapper;
import cn.edu.imufe.po.Paper;
import cn.edu.imufe.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /*
     * @Author 李雷
     * @Description
     * 添加试卷 不允许重名
     * @CreateDate 9:45 2020/12/16
     * @UpdateDate 9:45 2020/12/16
     * @Param [paper]
     * @return java.lang.Integer
     **/
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
    public Paper getPaperByPaperNameWithoutId(Paper paper) {
        return paperMapper.getPaperByPaperNameWithoutId(paper);
    }

    @Override
    public Paper getPaperByPaperId(Long paperId) {
        return paperMapper.selectByPrimaryKey(paperId);
    }

    @Override
    public List<Paper> getAllPaper() {
        return paperMapper.getAll();
    }

    @Override
    public Integer deletePaperById(Long id) {
        return paperMapper.deleteByPrimaryKey(id);
    }

    /*
     * @Author 李雷
     * @Description
     * 更新试卷信息 不允许重名
     * @CreateDate 9:44 2020/12/16
     * @UpdateDate 9:44 2020/12/16
     * @Param [paper]
     * @return java.lang.Integer
     **/
    @Override
    public Integer updatePaper(Paper paper) {
        if (getPaperByPaperNameWithoutId(paper) != null) {return 0;}
        return paperMapper.updateByPrimaryKeySelective(paper);
    }
}
