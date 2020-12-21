package cn.edu.imufe.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.imufe.constant.MessageConstant;
import cn.edu.imufe.po.Answer;
import cn.edu.imufe.po.User;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.imufe.pojo.UserAnswerHistoryPojo;
import cn.edu.imufe.service.AnswerHistoryService;

/**
 * @author lilei
 *
 * 2020年11月19日
 */
@Controller
@RequestMapping(value="/answerHistory")
public class AnswerHistoryController extends BaseController {
	private final AnswerHistoryService answerHistoryService;
	@Autowired
	public AnswerHistoryController(AnswerHistoryService answerHistoryService) {
		this.answerHistoryService = answerHistoryService;
	}


	/*
	 * @Author 李雷
	 * @Description
	 * 查询用户答题历史
	 * @CreateDate
	 * @UpdateDate 14:25 2020/12/10
	 * @Param []
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@ResponseBody
	@RequestMapping(value="/getUserAnswerHistory",method=RequestMethod.POST)
	private Map<String,Object> getUserAnswerHistory(){
		Map<String,Object> modelMap=new HashMap<>();
		User user = (User) session.getAttribute("user");
		List<UserAnswerHistoryPojo> list = answerHistoryService.selectUserAnswerHistory(user.getId());
		if(list!=null)
		{
			modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
			modelMap.put("List", list);
		}else
		{
			modelMap.put(MessageConstant.MESSAGE, "没有答题历史");
		}
		return modelMap;
	}
	/*
	 * @Author 李雷
	 * @Description
	 * 分页查询登录用户答题历史
	 * @CreateDate 16:10 2020/12/21
	 * @UpdateDate 16:10 2020/12/21
	 * @Param [pageNow, pageSize]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@ResponseBody
	@RequestMapping(value="/getUserAnswerHistoryByPage",method=RequestMethod.POST)
	private Map<String,Object> getUserAnswerHistoryByPage(@RequestParam Integer pageNow, @RequestParam Integer pageSize){
		Map<String,Object> modelMap=new HashMap<>();
		User user = (User) session.getAttribute("user");
		PageInfo<UserAnswerHistoryPojo> pageInfo = (PageInfo<UserAnswerHistoryPojo>) answerHistoryService.selectUserAnswerHistoryByPage(user.getId(), pageNow,pageSize);
		if(pageInfo != null)
		{
			modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
			modelMap.put("pageInfo", pageInfo);
		}else
		{
			modelMap.put(MessageConstant.MESSAGE, "没有答题历史！");
		}
		return modelMap;
	}
}
