package cn.edu.imufe.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
import cn.edu.imufe.entity.Answerhistory;
import cn.edu.imufe.entity.User;
import cn.edu.imufe.service.AnswerHistoryService;
import cn.edu.imufe.service.AnswerService;
import cn.edu.imufe.util.ComparasionOfSqlUtils;
import cn.edu.imufe.util.DbUtil;
import cn.edu.imufe.util.ResultSetUtil;

/**
 * @author lilei
 *
 * 2020年9月25日
 */
@Controller
@RequestMapping("/sql")
public class SqlController extends BaseController{
	private final AnswerService answerService;
	private final AnswerHistoryService answerHistoryService;
	@Autowired
	public SqlController(AnswerService answerService, AnswerHistoryService answerHistoryService) {
		this.answerService = answerService;
		this.answerHistoryService = answerHistoryService;
	}
	
	private final String MESSAGE = "message";

	/**
	 * @功能	预览
	 * @参数	SQL语句
	 * @返回值
	 */
	@RequestMapping(value="/preview",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> preview(@RequestParam String sql){
		Map<String,Object> modelMap=new HashMap<>();
		Connection connection = DbUtil.getInstance().getConnection();
		try {
			PreparedStatement pstat = connection.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			ResultSetUtil rsu = new ResultSetUtil(rs);
			List<ArrayList<Object>> previewlist = rsu.getTableSetByRow();
			
			if(previewlist!=null)
			{
				modelMap.put(MESSAGE, "success");
				modelMap.put("previewlist", previewlist);
			}else 
			{
				modelMap.put(MESSAGE, "sql错误！或查询为空！");				
			}
		} catch (SQLException e) {
			//显示异常到控制台
			//e.printStackTrace();
			modelMap.put(MESSAGE, e.getMessage());
		}
		return modelMap;
	}

	/***
	 *
	 * @param sqlstring 用户写的SQlString 一起返回后台
	 * @param id 从题目列表点进去的题目 会把题目id+ 用户答案一并返回这个函数
	 * @return 返回值代表着正确的sql或者错误sql
	 * @author zhangman
	 */
	@RequestMapping(value="compare_sql",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> CompareSql(@RequestParam String sqlstring,@RequestParam int id) {
		Map<String,Object> modelMap=new HashMap<>();
		String result="Different";
		if(sqlstring!=null && !sqlstring.equals(""))
		{
			//从题目列表点进去 会把某道题的id存起来  然后比较函数会把 id+string一起返回给后台
			//TODO 由返回的id搜到题目和答案  
			cn.edu.imufe.entity.Answer a1=answerService.selectByPrimaryKey(id);
			//TODO 由上面的答案 与用户sql进行比较 ，得出String字符串返回的结果 需要调用ComparasionOfSqlUtils
			result=ComparasionOfSqlUtils.SQLOfComparasion(a1.getSolution(),sqlstring);
			modelMap.put("message", "success");
		}else 
		{
			modelMap.put("message", "请填写答案！");
		}
		modelMap.put("result", result);
		return modelMap;
	}
	/*
	 * @Author 李雷
	 * @Description //答案对比并添加答题历史，已有的答题历史进行覆盖 lilei
	 * @Date 2020年11月25日下午2:46:38
	 * @Param [sqlString, id]
	 * @return java.util.Map<java.lang.String,java.lang.Object> message是否进行比对， result比对结果
	 **/
	@RequestMapping(value="compareSqlAddHistory",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> compareSqlAddHistory(@RequestParam String sqlString,@RequestParam String id) {
		Map<String,Object> modelMap=new HashMap<>();
		if(sqlString!=null && !sqlString.equals(""))
		{
			Answer answer = answerService.selectByPrimaryKey(Integer.parseInt(id));
			String result = ComparasionOfSqlUtils.SQLOfComparasion(answer.getSolution(),sqlString);
			int status;
			switch(result) {
				case "Same":
					status = 1;
					break;
				case "Different":
					status = 0;
					break;
				default:
					status = 2;
					
			}
			User record = (User) session.getAttribute("user");
			Answerhistory answerhistory = new Answerhistory();
			Answerhistory replace;
			replace = answerHistoryService.selectByUserIdAndAnswerId(record.getId(),Integer.parseInt(id));
			
			answerhistory.setUserId(record.getId());
			answerhistory.setAnswerId(Integer.parseInt(id));
			answerhistory.setUserAnswers(sqlString);
			answerhistory.setQuestionStatus(status);
			
			if(replace == null) {
				answerHistoryService.insert(answerhistory);
			}else {
				answerhistory.setId(replace.getId());
				answerHistoryService.updateByPrimaryKey(answerhistory);
			}
			
			modelMap.put("result", result);
			modelMap.put(MESSAGE, "success");
		}else 
		{
			modelMap.put(MESSAGE, "请填写答案！");
		}
		return modelMap;
	}
	
	
}
