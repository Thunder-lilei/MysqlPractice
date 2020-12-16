package cn.edu.imufe.test;

import cn.edu.imufe.po.Paper;
import cn.edu.imufe.service.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <h3>MysqlPractice</h3>
 * <p></p>
 *
 * @author : 李雷
 * @date : 2020-12-16 15:34
 **/
public class PaperTest extends BaseTest{
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserRoleService userRoleService;
    @Autowired
    AnswerService answerService;
    @Autowired
    AnswerHistoryService answerHistoryService;
    @Autowired
    PaperService paperService;
    @Autowired
    PaperAnswerService paperAnswerService;

    @Test
    public void getResultByPaperIdAndAnswerId() {
        System.out.println(paperAnswerService.getResultByPaperIdAndAnswerId(1L,1L));
    }
    @Test
    public void getAllPaper() {
        List<Paper> papers = paperService.getAllPaper();
        papers.forEach(temp-> System.out.println(temp.getPaperName()));
    }
    @Test
    public void deletePaper() {
//		System.out.println(paperService.deletePaperById(3L));
        System.out.println(paperAnswerService.deletePaperAnswerByPaperId(3L));
    }
    @Test
    public void addPaper() {
        Paper paper = new Paper();
        paper.setClassId(2L);
        paper.setPaperName("测试试卷");
        System.out.println(paperService.addPaper(paper));
    }
    @Test
    public void addPaperAnswer() {
        System.out.println(paperAnswerService.addPaperAnswer(1L,1L));
    }
    @Test
    public void getPaperByPaperName() {
        Paper paper = paperService.getPaperByPaperName("测试试卷");
        System.out.println(paper);
    }
    @Test
    public void getPaperByPaperNameWithoutId() {
        Paper paper = new Paper();
        paper.setId(1L);
        paper.setPaperName("测试试卷");
        paper = paperService.getPaperByPaperNameWithoutId(paper);
        System.out.println(paper);
    }
}
