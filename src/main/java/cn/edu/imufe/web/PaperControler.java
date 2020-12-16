package cn.edu.imufe.web;

import cn.edu.imufe.po.Answer;
import cn.edu.imufe.po.Paper;
import cn.edu.imufe.service.AnswerService;
import cn.edu.imufe.service.PaperAnswerService;
import cn.edu.imufe.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private final AnswerService answerService;
    @Autowired
    public PaperControler(PaperService paperService, PaperAnswerService paperAnswerService, AnswerService answerService) {
        this.paperService = paperService;
        this.paperAnswerService = paperAnswerService;
        this.answerService = answerService;
    }
    private final String MESSAGE = "message";
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
            modelMap.put(MESSAGE,"请检查填写的试卷信息是否重名！");
            return modelMap;
        }
        paper = paperService.getPaperByPaperName(paperName);
        if (answerId == null) {
            modelMap.put(MESSAGE,"请选择至少一道题目！");
            return modelMap;
        }else {
            for(Long l : answerId) {
                paperAnswerService.addPaperAnswer(paper.getId(),l);
            }
        }
        modelMap.put(MESSAGE,"成功生成试卷:"+paperName);
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取试卷信息
     * 获取试卷ID
     * 返回试卷信息和试卷包含的题目信息
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
        List<Answer> answerList = new ArrayList<>();
        answerIdList.forEach(temp->answerList.add(answerService.selectByPrimaryKey(temp)));
        if (paper == null) {
            modelMap.put(MESSAGE,"获取试卷信息失败！");
        }
        modelMap.put("paper",paper);
        modelMap.put("answerList",answerList);
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
            modelMap.put(MESSAGE,"暂时没有试卷！");
        }
        modelMap.put("paperList",paperList);
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 删除试卷
     * 同时删除试卷与试题的对应关系
     * @CreateDate 9:02 2020/12/16
     * @UpdateDate 9:02 2020/12/16
     * @Param [paperId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value="/deletePaper",method= RequestMethod.POST)
    private Map<String,Object> deletePaper(@RequestParam Long paperId){
        Map<String,Object> modelMap=new HashMap<>();
        if (!paperService.deletePaperById(paperId).equals(0) && !paperAnswerService.deletePaperAnswerByPaperId(paperId).equals(0)) {
            modelMap.put(MESSAGE,"删除成功！");
            return modelMap;
        }
        modelMap.put(MESSAGE,"删除失败！");
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 更新试卷信息
     * @CreateDate 10:24 2020/12/16
     * @UpdateDate 10:24 2020/12/16
     * @Param [paper]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value="/updatePaper",method= RequestMethod.POST)
    private Map<String,Object> updatePaper(Paper paper){
        Map<String,Object> modelMap=new HashMap<>();
        if (paperService.updatePaper(paper).equals(0)) {
            modelMap.put(MESSAGE,"请尝试更换试卷名！");
            return modelMap;
        }
        modelMap.put(MESSAGE,"更新成功！");
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 试卷加题
     * @CreateDate 13:28 2020/12/16
     * @UpdateDate 13:28 2020/12/16
     * @Param [paperId, answerId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value="/addPaperAnswer",method= RequestMethod.POST)
    private Map<String,Object> addPaperAnswer(@RequestParam Long paperId, @RequestParam Long answerId){
        Map<String,Object> modelMap=new HashMap<>();
        if (paperAnswerService.addPaperAnswer(paperId,answerId).equals(0)) {
            modelMap.put(MESSAGE,"重复添加！");
            return modelMap;
        }
        modelMap.put(MESSAGE,"添加成功！");
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 试卷删题
     * @CreateDate 13:28 2020/12/16
     * @UpdateDate 13:28 2020/12/16
     * @Param [paperId, answerId]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value="/deletePaperAnswer",method= RequestMethod.POST)
    private Map<String,Object> deletePaperAnswer(@RequestParam Long paperId, @RequestParam Long answerId){
        Map<String,Object> modelMap=new HashMap<>();
        if (paperAnswerService.deletePaperAnswerByPaperIdAndAnswerId(paperId,answerId).equals(0)) {
            modelMap.put(MESSAGE,"移除失败");
            return modelMap;
        }
        modelMap.put(MESSAGE,"移除成功！");
        return modelMap;
    }

}
