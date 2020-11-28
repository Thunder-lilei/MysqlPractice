package cn.edu.imufe.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
	@Autowired
	private AnswerService answerservice;
	
	private static final String MESSAGE = "message";
	private static final String MESSAGE_SUCCESS = "success";
	private static final String MESSAGE_LOSE_DELETE = "删除失败";
	private static final String MESSAGE_LOSE_UPDATE = "修改失败";
	private static final String MESSAGE_LOSE_INSERT = "插入失败";
	private static final String MESSAGE_LOSE_SELECTEMPTY = "查询为空";
	private static final String MESSAGE_LOSE_SELECTTITLE = "请选择题目";
	private static final String MESSAGE_LOSE_LESSTITLE = "随机题目数量不足";
	private static final String MESSAGE_LOSS_LESSANSWER = "题库不足";
	private static final String REQUEST_RANDOMLIST = "randomList";
	private static final String REQUEST_ALLLIST = "allList";
	
	/**
	 * @功能	根据题目id获取题目信息
	 * @参数	id
	 * @返回值 返回完整的参数信息
	 */
	@ResponseBody
	@RequestMapping(value="/getTitle",method=RequestMethod.GET)
	private Map<String,Object> getTitle(@RequestParam String id){
		Map<String,Object> modelMap=new HashMap<>();
		if(id!=null && id!="") 
		{
			Answer answer = answerservice.selectByPrimaryKey(Integer.parseInt(id));
			if(answer!=null)
			{
				modelMap.put(MESSAGE, MESSAGE_SUCCESS);
				modelMap.put("answer", answer);
			}else  
			{
				modelMap.put(MESSAGE, MESSAGE_LOSE_SELECTEMPTY);
			}
		}else 
		{
			modelMap.put(MESSAGE, MESSAGE_LOSE_SELECTTITLE);
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
		List<Integer> allid = answerservice.selectAllid();
		Integer Random = 5;
		//随机抽取的题目数量
		if(allid!=null)
		{
			@SuppressWarnings("unchecked")
			List<Integer> randomlist = RandomList.createRandomList(allid, Random);
			//获取随机题目id allid为题库中全部id Random为随机抽取的数量
			if(!Random.equals(randomlist.size())) 
			{
				modelMap.put(MESSAGE, MESSAGE_LOSE_LESSTITLE);
			}else
			{
				modelMap.put(MESSAGE, MESSAGE_SUCCESS);
				modelMap.put(REQUEST_RANDOMLIST, randomlist);
			}
		}else  
		{
			modelMap.put(MESSAGE, MESSAGE_LOSS_LESSANSWER);
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
		List<AnswerPojo> allidwithtitle = answerservice.selectAllIdwithTitle();
		if(allidwithtitle!=null)
		{
			modelMap.put(MESSAGE, MESSAGE_SUCCESS);
			modelMap.put(REQUEST_ALLLIST, allidwithtitle);
		}else  
		{
			modelMap.put(MESSAGE, MESSAGE_LOSS_LESSANSWER);
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
	private Map<String,Object> getQuizsByUserId(){
		Map<String,Object> modelMap=new HashMap<>();
		List<AnswerPojo> allidwithtitle = answerservice.selectAllIdwithTitle();
		if(allidwithtitle!=null)
		{
			modelMap.put(MESSAGE, MESSAGE_SUCCESS);
			modelMap.put(REQUEST_ALLLIST, allidwithtitle);
		}else  
		{
			modelMap.put(MESSAGE, MESSAGE_LOSS_LESSANSWER);
		}
		return modelMap;
	}
	/**
	 * @功能	创建新的题目
	 * @参数	Answer
	 * @返回值 message
	 */
	@ResponseBody
	@RequestMapping(value="/createQuiz",method=RequestMethod.GET)
	private Map<String,Object> createQuiz(@RequestBody Answer answer){
		Map<String,Object> modelMap=new HashMap<>();
		Integer index = answerservice.insertSelective(answer);
		if(index.equals(1)) 
		{
			modelMap.put(MESSAGE, MESSAGE_SUCCESS);
		}else 
		{
			modelMap.put(MESSAGE, MESSAGE_LOSE_INSERT);
		}
		return modelMap;
	}
	/**
	 * @功能	删除指定题目
	 * @参数	题目id
	 * @返回值 message
	 */
	@ResponseBody
	@RequestMapping(value="/deleteQuiz",method=RequestMethod.GET)
	private Map<String,Object> deleteQuiz(@RequestParam Integer id){
		Map<String,Object> modelMap=new HashMap<>();
		Integer index = answerservice.deleteByPrimaryKey(id);
		if(index.equals(1)) 
		{
			modelMap.put(MESSAGE, MESSAGE_SUCCESS);
		}else 
		{
			modelMap.put(MESSAGE, MESSAGE_LOSE_DELETE);
		}
		return modelMap;
	}
	/**
	 * @功能	修改题目
	 * @参数	新的题目信息
	 * @返回值 message
	 */
	@ResponseBody
	@RequestMapping(value="/updateQuiz",method=RequestMethod.GET)
	private Map<String,Object> updateQuiz(@RequestParam Answer answer){
		Map<String,Object> modelMap=new HashMap<>();
		Integer index = answerservice.updateByPrimaryKeySelective(answer);
		if(index.equals(1)) 
		{
			modelMap.put(MESSAGE, MESSAGE_SUCCESS);
		}else 
		{
			modelMap.put(MESSAGE, MESSAGE_LOSE_UPDATE);
		}
		return modelMap;
	}
}
