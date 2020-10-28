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

import cn.edu.imufe.dao.AnswerDao;
import cn.edu.imufe.service.impl.AnswerServiceImpl;
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
	@Autowired
	AnswerDao answerdao;
	@Autowired
	AnswerServiceImpl answerServiceimpl;
//	@Autowired
//	private SqlService sqlService;
	/**
	 * @功能	预览
	 * @参数	SQL语句
	 * @返回值
	 */
	@RequestMapping(value="/preview",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> preview(@RequestParam String sql){
		Map<String,Object> modelMap=new HashMap<>();
		System.out.println(sql);
		Connection connection = DbUtil.getInstance().getConnection();
		try {
			PreparedStatement pstat = connection.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			ResultSetUtil rsu = new ResultSetUtil(rs);
			List<ArrayList<Object>> previewlist = rsu.getTableSetByRow();
			
			if(previewlist!=null)
			{
				modelMap.put("message", "success");
				modelMap.put("previewlist", previewlist);
			}else 
			{
				modelMap.put("message", "sql错误！或查询为空！");				
			}
		} catch (SQLException e) {
			//显示异常到控制台
			//e.printStackTrace();
			modelMap.put("message", e.getMessage());
		}
		return modelMap;
	}
	/***
	 * 
	 * @param sqlstring 用户写的SQlString 一起返回后台 
	 * @param id 从题目列表点进去的题目 会把题目id+ 用户答案一并返回这个函数
	 * @return 返回值代表着正确的sql或者错误sql 
	 */
	@RequestMapping(value="compare_sql",method = RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> CompareSql(@RequestParam String sqlstring,@RequestParam int id) {
		Map<String,Object> modelMap=new HashMap<>();
		String result="Different";
		if(sqlstring!=null && sqlstring!="") 
		{
			//从题目列表点进去 会把某道题的id存起来  然后比较函数会把 id+string一起返回给后台
			//TODO 由返回的id搜到题目和答案  
			cn.edu.imufe.entity.Answer a1=answerServiceimpl.selectByPrimaryKey(id);
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
}
