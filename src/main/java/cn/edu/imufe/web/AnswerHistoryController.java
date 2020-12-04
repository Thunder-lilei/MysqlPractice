package cn.edu.imufe.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.imufe.entity.User;
import cn.edu.imufe.pojo.AnswerHistoryPojo;
import cn.edu.imufe.service.AnswerHistoryService;

/**
 * @author lilei
 *
 * 2020年11月19日
 */
@Controller
@RequestMapping(value="/answerhistory")
public class AnswerHistoryController extends BaseController {
	@Autowired
	AnswerHistoryService answerHistoryService;
	
	private final String MESSAGE = "message";
	private final String MESSAGE_SUCCESS = "success";
	
	/**
	 * @功能	发送发送用户答题历史
	 * @参数	无参数
	 * @返回值 返回所有答题历史 list
	 */
	@ResponseBody
	@RequestMapping(value="/getUserAnswerHistory",method=RequestMethod.GET)
	private Map<String,Object> getUserAnswerHistory(){
		Map<String,Object> modelMap=new HashMap<>();
		User user = (User) session.getAttribute("user");
		List<AnswerHistoryPojo> list = answerHistoryService.selectUserAnswerHistory(user.getId());
		if(list!=null)
		{
			modelMap.put(MESSAGE, MESSAGE_SUCCESS);
			modelMap.put("List", list);
		}else  
		{
			modelMap.put(MESSAGE, "没有答题历史");
		}
		return modelMap;
	}
}
