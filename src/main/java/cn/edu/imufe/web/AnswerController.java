package cn.edu.imufe.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.imufe.constant.MessageConstant;
import cn.edu.imufe.po.Answer;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.imufe.pojo.AnswerBaseInfoPojo;
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
	private final AnswerService answerService;
	@Autowired
	public AnswerController(AnswerService answerservice) {
		this.answerService = answerservice;
	}

	/*
	 * @Author 李雷
	 * @Description
	 * 查询题目信息
	 * @CreateDate
	 * @UpdateDate 14:36 2020/12/10
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@ResponseBody
	@RequestMapping(value="/getTitle",method=RequestMethod.POST)
	private Map<String,Object> getTitle(@RequestParam String id){
		Map<String,Object> modelMap=new HashMap<>();
		if(id!=null && !id.equals(""))
		{
			Answer answer = answerService.selectByPrimaryKey(Long.parseLong(id));
			if(answer!=null)
			{
				modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
				modelMap.put("answer", answer);
			}else
			{
				modelMap.put(MessageConstant.MESSAGE, "查询为空");
			}
		}else
		{
			modelMap.put(MessageConstant.MESSAGE, "请选择题目");
		}

		return modelMap;
	}
	/*
	 * @Author 李雷
	 * @Description
	 * 随机发送五道题目
	 * @CreateDate
	 * @UpdateDate 14:28 2020/12/10
	 * @Param []
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@ResponseBody
	@RequestMapping(value="/getRandomQuizs",method=RequestMethod.POST)
	private Map<String,Object> getRandomQuizs(){
		Map<String,Object> modelMap=new HashMap<>();
		List<Long> allid = answerService.selectAllId();
		Integer randomSize = 5;
		//随机抽取的题目数量
		if(allid!=null)
		{
			@SuppressWarnings("unchecked")
			List<Integer> randomlist = RandomList.createRandomList(allid, randomSize);
			//获取随机题目id allid为题库中全部id Random为随机抽取的数量
			if(!randomSize.equals(randomlist.size()))
			{
				modelMap.put(MessageConstant.MESSAGE, "随机题目数量不足");
			}else
			{
				modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
				modelMap.put("randomList", randomlist);
			}
		}else
		{
			modelMap.put(MessageConstant.MESSAGE, "题库不足");
		}
		return modelMap;
	}
	/*
	 * @Author 李雷
	 * @Description
	 * 获取所有题目的id
	 * @CreateDate
	 * @UpdateDate 14:28 2020/12/10
	 * @Param []
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@ResponseBody
	@RequestMapping(value="/getAllQuizBaseInfo",method=RequestMethod.POST)
	private Map<String,Object> getAllQuizBaseInfo(){
		Map<String,Object> modelMap=new HashMap<>();
		List<AnswerBaseInfoPojo> answerBaseInfoPojoList = answerService.getAllAnswerBaseInfo();
		if(answerBaseInfoPojoList !=null)
		{
			modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
			modelMap.put("allList", answerBaseInfoPojoList);
		}else
		{
			modelMap.put(MessageConstant.MESSAGE, "题库不足");
		}
		return modelMap;
	}
	/*
	 * @Author 李雷
	 * @Description
	 * 分页查询题库
	 * @CreateDate 15:09 2020/12/21
	 * @UpdateDate 15:09 2020/12/21
	 * @Param [pageNow, pageSize]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@ResponseBody
	@RequestMapping(value="/getAllQuizBaseInfoByPage",method=RequestMethod.POST)
	private Map<String,Object> getAllQuizBaseInfoByPage(@RequestParam Integer pageNow,@RequestParam Integer pageSize){
		Map<String,Object> modelMap=new HashMap<>();
		PageInfo<Answer> pageInfo = (PageInfo<Answer>) answerService.getAllAnswerBaseInfo(pageNow,pageSize);
		if(pageInfo != null)
		{
			modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
			modelMap.put("pageInfo", pageInfo);
		}else
		{
			modelMap.put(MessageConstant.MESSAGE, "题库不足");
		}
		return modelMap;
	}
	/*
	 * @Author 李雷
	 * @Description
	 * 添加新题目
	 * @CreateDate
	 * @UpdateDate 14:28 2020/12/10
	 * @Param [question, solution]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@ResponseBody
	@RequestMapping(value="/createQuiz",method=RequestMethod.POST)
	private Map<String,Object> createQuiz(@RequestParam String question,@RequestParam String solution){
		Answer answer = new Answer();
		answer.setQuestion(question);
		answer.setSolution(solution);
		Map<String,Object> modelMap=new HashMap<>();
		Integer index = answerService.insertSelective(answer);
		if(index.equals(1))
		{
			modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
		}else
		{
			modelMap.put(MessageConstant.MESSAGE, "插入失败");
		}
		return modelMap;
	}
	/*
	 * @Author 李雷
	 * @Description
	 * 删除指定题目
	 * @CreateDate
	 * @UpdateDate 14:27 2020/12/10
	 * @Param [id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@ResponseBody
	@RequestMapping(value="/deleteQuiz",method=RequestMethod.POST)
	private Map<String,Object> deleteQuiz(@RequestParam Long id){
		Map<String,Object> modelMap=new HashMap<>();
		Integer index = answerService.deleteByPrimaryKey(id);
		if(index.equals(1))
		{
			modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
		}else
		{
			modelMap.put(MessageConstant.MESSAGE, "删除失败");
		}
		return modelMap;
	}
	/*
	 * @Author 李雷
	 * @Description
	 * 修改试题
	 * 不允许重名
	 * @CreateDate 14:27 2020/12/10
	 * @UpdateDate 13:53 2020/12/16
	 * @Param [answer]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@ResponseBody
	@RequestMapping(value="/updateQuiz",method=RequestMethod.POST)
	private Map<String,Object> updateQuiz(Answer answer){
		Map<String,Object> modelMap=new HashMap<>();
		Integer index = answerService.updateByPrimaryKeySelective(answer);
		if(index.equals(1))
		{
			modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
		}else
		{
			modelMap.put(MessageConstant.MESSAGE, "修改失败");
		}
		return modelMap;
	}
}
