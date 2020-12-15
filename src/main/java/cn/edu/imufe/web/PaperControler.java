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
import java.util.List;
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
     * 生成试卷
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
    /*
     * @Author 李雷
     * @Description
     * 获取试卷信息
     * 获取试卷ID
     * 返回试卷信息和试卷包含的题目id
     * @CreateDate 21:24 2020/12/15
     * @UpdateDate 21:24 2020/12/15
     * @Param [paperId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value="/getPaper",method= RequestMethod.POST)
    private Map<String,Object> getPaper(@RequestParam Long paperId){
        Map<String,Object> modelMap=new HashMap<>();
        Paper paper = paperService.getPaperByPaperId(paperId);
        List<Long> answerIdList = paperAnswerService.getAnswerIdByPaperId(paperId);
        if (paper == null || answerIdList == null) {
            modelMap.put("message","获取试卷信息失败！");
        }
        modelMap.put("paper",paper);
        modelMap.put("answerIdList",answerIdList);
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取所有试卷
     * @CreateDate 21:32 2020/12/15
     * @UpdateDate 21:32 2020/12/15
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value="/getAllPaper",method= RequestMethod.POST)
    private Map<String,Object> getAllPaper(){
        Map<String,Object> modelMap=new HashMap<>();
        List<Paper> paperList = paperService.getAllPaper();
        if (paperList == null) {
            modelMap.put("message","暂时没有试卷！");
        }
        modelMap.put("paperList",paperList);
        return modelMap;
    }
}
