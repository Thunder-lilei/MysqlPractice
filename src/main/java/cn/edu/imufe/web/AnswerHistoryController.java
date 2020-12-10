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
		List<AnswerHistoryPojo> list = answerHistoryService.selectUserAnswerHistory(user.getId());
		if(list!=null)
		{
			modelMap.put("message", "success");
			modelMap.put("List", list);
		}else  
		{
			modelMap.put("message", "没有答题历史");
		}
		return modelMap;
	}
}
