package cn.edu.imufe.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.imufe.constant.MessageConstant;
import cn.edu.imufe.po.Answer;
import cn.edu.imufe.po.AnswerHistory;
import cn.edu.imufe.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	/*
	 * @Author 李雷
	 * @Description
	 * 预览 获取sql 返回结果list
	 * @CreateDate
	 * @UpdateDate 14:18 2020/12/10
	 * @Param [sql]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping(value="/preview",method = RequestMethod.POST)
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
				modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
				modelMap.put("previewlist", previewlist);
			}else
			{
				modelMap.put(MessageConstant.MESSAGE, "sql错误！或查询为空！");
			}
		} catch (SQLException e) {
			//显示异常到控制台
			//e.printStackTrace();
			modelMap.put(MessageConstant.MESSAGE, e.getMessage());
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
	public Map<String,Object> CompareSql(@RequestParam String sqlstring,@RequestParam Long id) {
		Map<String,Object> modelMap=new HashMap<>();
		String result="Different";
		if(sqlstring!=null && !sqlstring.equals(""))
		{
			//从题目列表点进去 会把某道题的id存起来  然后比较函数会把 id+string一起返回给后台
			//TODO 由返回的id搜到题目和答案
			cn.edu.imufe.po.Answer a1=answerService.selectByPrimaryKey(id);
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
	 * @Description
	 * 答案对比并添加答题历史，已有的答题历史进行覆盖
	 * @CreateDate 2020年11月25日下午2:46:38
	 * @UpdateDate 14:21 2020/12/10
	 * @Param [sqlString, id]
	 * @return java.util.Map<java.lang.String,java.lang.Object>
	 **/
	@RequestMapping(value="/compareSqlAddHistory",method = RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> compareSqlAddHistory(@RequestParam String sqlString,@RequestParam String id) {
		Map<String,Object> modelMap=new HashMap<>();
		if(sqlString!=null && !sqlString.equals(""))
		{
			Answer answer = answerService.selectByPrimaryKey(Long.parseLong(id));
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
			AnswerHistory replace;
			replace = answerHistoryService.selectByUserIdAndAnswerId(record.getId(),Long.parseLong(id));

			AnswerHistory answerhistory = new AnswerHistory(null,record.getId(),Long.parseLong(id),
					sqlString,status);

			if(replace == null) {
				answerHistoryService.insert(answerhistory);
			}else {
				answerhistory.setId(replace.getId());
				answerHistoryService.updateByPrimaryKey(answerhistory);
			}

			modelMap.put("result", result);
			modelMap.put(MessageConstant.MESSAGE, MessageConstant.MESSAGE_SUCCESS);
		}else
		{
			modelMap.put(MessageConstant.MESSAGE, "请填写答案！");
		}
		return modelMap;
	}


}
