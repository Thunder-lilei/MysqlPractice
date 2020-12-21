package cn.edu.imufe.test;

import cn.edu.imufe.po.Answer;
import cn.edu.imufe.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
public class AnswerTest extends BaseTest{
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
    public void getAnswerByQuestionWithoutId() {
        Answer answer  = new Answer();
        answer.setQuestion("查询全体学生的详细记录");
        answer.setId(1L);
        answer = answerService.getAnswerByQuestionWithoutId(answer);
        System.out.println(answer);
    }
    @Test
    public void selectAnswerByQuestion() {
        System.out.println(answerService.selectByQuestion("查询全体学生的详细记录"));
    }
    @Test
    public void getAnswerIdByPaperId() {
        List<Long> paperAnswerList = paperAnswerService.getAnswerIdByPaperId(1L);
        paperAnswerList.forEach(temp-> System.out.println(temp));
    }
    @Test
    public void findPage() {
        PageInfo<Answer> answerPageInfo = (PageInfo<Answer>) answerService.getAllAnswerBaseInfo(1,10);
        List<Answer> answerList = answerPageInfo.getList();
        answerList.forEach(temp-> System.out.println(temp.getQuestion()));
        System.out.println("结果："+answerPageInfo.toString());
    }
}
