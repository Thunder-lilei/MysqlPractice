package cn.edu.imufe.ActivitiesTest;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import cn.edu.imufe.pojo.Table;
import cn.edu.imufe.util.DbUtil;
import cn.edu.imufe.util.ResultSetUtil;


public class ActivitiesServiceTest{
//	@Autowired
//	private SqlService sqlService;
	public String ReturnMessage;

	public String getReturnMessage() {
		return ReturnMessage;
	}

	public void setReturnMessage(String returnMessage) {
		ReturnMessage = returnMessage;
	}
	
	@Test
	public void testsql() {
		Connection connection = DbUtil.getInstance().getConnection();
		try {
			PreparedStatement pstat = connection.prepareStatement("select * from Student");
			ResultSet rs = pstat.executeQuery();
//			System.out.println(rs.getRow());
			ResultSetUtil rsu = new ResultSetUtil(rs);
			
			System.out.println("行数:" + rsu.getRowSize());
			System.out.println("列数:" + rsu.getColumnSize());

			System.out.println("字段名:" + rsu.getTableColumnNameSet());
			System.out.println("字段类型:" + rsu.getColumnTypeSet());

			System.out.println("首行ascii的列表" + rsu.getFirstRowSet());
			System.out.println("首行ascii的和:" + rsu.getFirstRowSum());

			System.out.println("首列ascii的列表:" + rsu.getFirstColumnSet());
			System.out.println("首列ascii的和:" + rsu.getFirstColumnSum());
			System.out.println("每行和的列表:" + rsu.getRowSet());
			System.out.println("表总和:" + rsu.getAllTableSum());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 如果出现错误，那么就不用进行比较，直接返回（打印）错误的原因 如果没有出现错误，那么才进行比较
	 */
	@Test
	public void testentity() {
		// 判断是否是语法错误抛出去的原因
		boolean flag = false;

		Connection connection = DbUtil.getInstance().getConnection();
		Table table = new Table();
		Table table2 = new Table();
		System.out.println("开始");
		// 答案结果
		try {
			PreparedStatement pstat = connection.prepareStatement("select Sname as name,Sage as age from Student where Sage >=20");
			ResultSet rs = pstat.executeQuery();
			ResultSetUtil rsu = new ResultSetUtil(rs);
			System.out.println("获取第二个(答案结果)table");
			table2.setAll(rsu.getRowSize(), rsu.getColumnSize(), rsu.getAllTableSum(), rsu.getFirstRowSum(), rsu.getFirstRowSet(), rsu.getFirstColumnSet(), rsu.getRowSet());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 用户结果
		System.out.println("用户结果查询开始");
		try {
			PreparedStatement pstat = connection.prepareStatement("select Sname as name,Sage as age from Student where Sage >=20");
			ResultSet rs = pstat.executeQuery();
			ResultSetUtil rsu = new ResultSetUtil(rs);
			table.setAll(rsu.getRowSize(), rsu.getColumnSize(), rsu.getAllTableSum(), rsu.getFirstRowSum(), rsu.getFirstRowSet(), rsu.getFirstColumnSet(), rsu.getRowSet());
			// 1:查询无结果 错误
			if (table.getRows().equals(0)) {
				System.out.println("no " + "行数:" + table.getRows());
			}
			System.out.println("获取第一个(用户结果)table");
			flag = true;
		} catch (SQLException e) {
			flag=false;
			ReturnMessage=e.getMessage();
			System.err.println(ReturnMessage);
		}
		if (flag == true) {
			if (table.equals(table2))
				ReturnMessage="Same sql";
			else {
				ReturnMessage="different sql";
			}
			System.out.println(ReturnMessage);
		}
		
	}
}
