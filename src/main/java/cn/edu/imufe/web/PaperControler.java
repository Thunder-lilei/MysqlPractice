package cn.edu.imufe.web;

import cn.edu.imufe.constant.PageUrlConstant;
import cn.edu.imufe.po.Paper;
import cn.edu.imufe.po.PaperAnswer;
import cn.edu.imufe.service.PaperAnswerService;
import cn.edu.imufe.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * <h3>MysqlPractice</h3>
 * <p>试卷</p>
 *
 * @author : 李雷
 * @date : 2020-12-15 20:26
 **/
@Controller
@RequestMapping(value="/paper")
public class PaperControler extends BaseController {
    private final PaperService paperService;
    private final PaperAnswerService paperAnswerService;
    @Autowired
    public PaperControler(PaperService paperService, PaperAnswerService paperAnswerService) {
        this.paperService = paperService;
        this.paperAnswerService = paperAnswerService;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取试卷名称 班级Id 选择的题目Id
     * 返回message信息
     * @CreateDate 21:03 2020/12/15
     * @UpdateDate 21:03 2020/12/15
     * @Param [paperName, classId, answerId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value="/addPaper",method= RequestMethod.POST)
    private Map<String,Object> addPaper(@RequestParam String paperName,@RequestParam Long classId,@RequestParam Long[] answerId){
        Map<String,Object> modelMap=new HashMap<>();
        Paper paper = new Paper();
        paper.setPaperName(paperName);
        paper.setClassId(classId);
        if (paperService.addPaper(paper).equals(0)) {
            modelMap.put("message","请检查填写的试卷信息是否重名！");
            return modelMap;
        }
        paper = paperService.getPaperByPaperName(paperName);
        if (answerId == null) {
            modelMap.put("message","请选择至少一道题目！");
            return modelMap;
        }else {
            for(Long l : answerId) {
                PaperAnswer paperAnswer = new PaperAnswer();
                paperAnswer.setPaperId(paper.getId());
                paperAnswer.setAnswerId(l);
                paperAnswerService.addPaperAnswer(paperAnswer);
            }
        }
        modelMap.put("message","成功生成试卷:"+paperName);
        return modelMap;
    }
}
