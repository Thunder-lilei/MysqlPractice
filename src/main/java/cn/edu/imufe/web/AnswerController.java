package cn.edu.imufe.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.imufe.entity.Answer;
import cn.edu.imufe.pojo.AnswerPojo;
import cn.edu.imufe.service.AnswerService;
import cn.edu.imufe.util.RandomList;


/**
 * @author lilei
 *
 * 2020年10月14日
 */
@Controller
@RequestMapping(value="/answer")
public class AnswerController extends BaseController {
	private final AnswerService answerservice;
	@Autowired
	public AnswerController(AnswerService answerservice) {
		this.answerservice = answerservice;
	}

	private final String MESSAGE = "message";
	private final String MESSAGE_SUCCESS = "success";
	
	/**
	 * @功能	根据题目id获取题目信息
	 * @参数	id
	 * @返回值 返回完整的参数信息
	 */
	@ResponseBody
	@RequestMapping(value="/getTitle",method=RequestMethod.GET)
	private Map<String,Object> getTitle(@RequestParam String id){
		Map<String,Object> modelMap=new HashMap<>();
		if(id!=null && !id.equals(""))
		{
			Answer answer = answerservice.selectByPrimaryKey(Integer.parseInt(id));
			if(answer!=null)
			{
				modelMap.put(MESSAGE, MESSAGE_SUCCESS);
				modelMap.put("answer", answer);
			}else  
			{
				modelMap.put(MESSAGE, "查询为空");
			}
		}else 
		{
			modelMap.put(MESSAGE, "请选择题目");
		}
		
		return modelMap;
	}
	/**
	 * @功能	发送五道随机题目
	 * @参数	暂时无参数
	 * @返回值 返回题目id list
	 */
	@ResponseBody
	@RequestMapping(value="/getRandomQuizs",method=RequestMethod.GET)
	private Map<String,Object> getRandomQuizs(){
		Map<String,Object> modelMap=new HashMap<>();
		List<Integer> allid = answerservice.selectAllId();
		Integer Random = 5;
		//随机抽取的题目数量
		if(allid!=null)
		{
			@SuppressWarnings("unchecked")
			List<Integer> randomlist = RandomList.createRandomList(allid, Random);
			//获取随机题目id allid为题库中全部id Random为随机抽取的数量
			if(!Random.equals(randomlist.size())) 
			{
				modelMap.put(MESSAGE, "随机题目数量不足");
			}else
			{
				modelMap.put(MESSAGE, MESSAGE_SUCCESS);
				modelMap.put("randomList", randomlist);
			}
		}else  
		{
			modelMap.put(MESSAGE, "题库不足");
		}
		return modelMap;
	}
	/**
	 * @功能	发送所有题目id
	 * @参数	无参数
	 * @返回值 返回所有题目id list
	 */
	@ResponseBody
	@RequestMapping(value="/getQuizs",method=RequestMethod.GET)
	private Map<String,Object> getQuizs(){
		Map<String,Object> modelMap=new HashMap<>();
		List<AnswerPojo> allidwithtitle = answerservice.selectAllIdWithTitle();
		if(allidwithtitle!=null)
		{
			modelMap.put(MESSAGE, MESSAGE_SUCCESS);
			modelMap.put("allList", allidwithtitle);
		}else  
		{
			modelMap.put(MESSAGE, "题库不足");
		}
		return modelMap;
	}
	/**
	 * @功能	创建新的题目
	 * @参数	Answer
	 * @返回值 message
	 */
	@ResponseBody
	@RequestMapping(value="/createQuiz",method=RequestMethod.POST)
	private Map<String,Object> createQuiz(@RequestParam String question,@RequestParam String solution){
		Answer answer = new Answer();
		answer.setQuestion(question);
		answer.setSolution(solution);
		Map<String,Object> modelMap=new HashMap<>();
		Integer index = answerservice.insertSelective(answer);
		if(index.equals(1)) 
		{
			modelMap.put(MESSAGE, MESSAGE_SUCCESS);
		}else 
		{
			modelMap.put(MESSAGE, "插入失败");
		}
		return modelMap;
	}
	/**
	 * @功能	删除指定题目
	 * @参数	题目id
	 * @返回值 message
	 */
	@ResponseBody
	@RequestMapping(value="/deleteQuiz",method=RequestMethod.POST)
	private Map<String,Object> deleteQuiz(@RequestParam Integer id){
		Map<String,Object> modelMap=new HashMap<>();
		Integer index = answerservice.deleteByPrimaryKey(id);
		if(index.equals(1)) 
		{
			modelMap.put(MESSAGE, MESSAGE_SUCCESS);
		}else 
		{
			modelMap.put(MESSAGE, "删除失败");
		}
		return modelMap;
	}
	/**
	 * @功能	修改题目
	 * @参数	新的题目信息
	 * @返回值 message
	 */
	@ResponseBody
	@RequestMapping(value="/updateQuiz",method=RequestMethod.POST)
	private Map<String,Object> updateQuiz(@RequestParam Integer id,@RequestParam String question,@RequestParam String solution){
		Map<String,Object> modelMap=new HashMap<>();
		Answer answer = new Answer();
		answer.setId(id);
		answer.setQuestion(question);
		answer.setSolution(solution);
		Integer index = answerservice.updateByPrimaryKeySelective(answer);
		if(index.equals(1)) 
		{
			modelMap.put(MESSAGE, MESSAGE_SUCCESS);
		}else 
		{
			modelMap.put(MESSAGE, "修改失败");
		}
		return modelMap;
	}
}
