package cn.edu.imufe.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.imufe.entity.Answer;
import cn.edu.imufe.entity.Answerhistory;
import cn.edu.imufe.entity.User;
import cn.edu.imufe.service.AnswerHistoryService;
import cn.edu.imufe.service.AnswerService;

/**
 * @author lilei
 *
 * 2020年11月19日
 */
@Controller
@RequestMapping(value="/answerhistory")
public class AnswerHistoryController extends BaseController {
	@Autowired
	private AnswerHistoryService answerhistoryservice;
	@Autowired
	private AnswerService answerservice;
	
	/**
	 * @功能	保存回答历史
	 * @参数	问题id 问题回答状态status
	 * @返回值 index.html以及message
	 */
	@ResponseBody
	@RequestMapping(value="/addAnswerHistory",method=RequestMethod.POST)
	private ModelAndView addAnswerHistory(@RequestParam String id,@RequestParam String status){
		ModelAndView mv = new ModelAndView("redirect:/student/quiz.html");
		User record = (User) session.getAttribute("user");
		Answerhistory answerhistory = new Answerhistory();
		//查找对应问题
		Answer answer = answerservice.selectByPrimaryKey(Integer.parseInt(id));
				
		//添加数据
		answerhistory.setUsername(record.getUsername());
		answerhistory.setAnswerId(record.getId());
		answerhistory.setUserAnswers(answer.getSolution());
		answerhistory.setQuestionStatus(Integer.parseInt(status));
		//插入
		if(answerhistoryservice.insert(answerhistory)==1) {
			mv.addObject("message", "success");
		}else {
			mv.addObject("message", "回答状态更新失败！");
		}
		
		return mv;
	}
}
