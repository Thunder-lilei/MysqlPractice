package cn.edu.imufe.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.edu.imufe.pojo.Table;

/**
 * 目的是为了将魏源和李雷写的sql比较改写成函数形式，方便使用
 * 
 * @author Zhang-Man
 *
 */
public class ComparasionOfSqlUtils {
	/**
	 * 对比成功返回true,对比失败返回false
	 * 
	 * @param s1 Student Answer
	 * @param s2 Standard Answer
	 * @return
	 */
	public static String SQLOfComparasion(String s1, String s2) {
		String ReturnMessage="Different";
		boolean flag = false;

		Connection connection = DbUtil.getInstance().getConnection();
		Table table = new Table();
		Table table2 = new Table();
		// 答案结果
		try {
			PreparedStatement pstat = connection.prepareStatement(s1);
			ResultSet rs = pstat.executeQuery();
			ResultSetUtil rsu = new ResultSetUtil(rs);
			System.out.println("获取第二个(答案结果)table");
			table2.setAll(rsu.getRowSize(), rsu.getColumnSize(), rsu.getAllTableSum(), rsu.getFirstRowSum(),
					rsu.getFirstRowSet(), rsu.getFirstColumnSet(), rsu.getRowSet());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 用户结果
		System.out.println("用户结果查询开始");
		try {
			PreparedStatement pstat = connection.prepareStatement(s2);
			ResultSet rs = pstat.executeQuery();
			ResultSetUtil rsu = new ResultSetUtil(rs);
			table.setAll(rsu.getRowSize(), rsu.getColumnSize(), rsu.getAllTableSum(), rsu.getFirstRowSum(),
					rsu.getFirstRowSet(), rsu.getFirstColumnSet(), rsu.getRowSet());
			// 1:查询无结果 错误
			if (table.getRows().equals(0)) {
				System.out.println("no " + "行数:" + table.getRows());
			}
			System.out.println("获取第一个(用户结果)table");
			flag = true;
		} catch (SQLException e) {
			flag = false;
			ReturnMessage = e.getMessage();
			System.err.println(ReturnMessage);
		}
		if (flag == true) {
			if (table.equals(table2))
				ReturnMessage="Same";
			else {
				ReturnMessage="Different";
			}
			System.out.println(ReturnMessage);
		}
		return ReturnMessage;
	}
	public static void main(String[] args) {
		
	}
}
