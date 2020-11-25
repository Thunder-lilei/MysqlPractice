package cn.edu.imufe.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.imufe.entity.Answer;
import cn.edu.imufe.entity.Answerhistory;
import cn.edu.imufe.entity.User;
import cn.edu.imufe.pojo.AnswerIdTitle;
import cn.edu.imufe.service.*;
import cn.edu.imufe.util.ComparasionOfSqlUtils;
import cn.edu.imufe.util.RandomList;

public  class ServicTest extends BaseTest{
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private AnswerService answerService;
	@Autowired
	private AnswerHistoryService answerHistoryService;
	
	private static final String RESULT = "result";
	private static final String MESSAGE = "message";
	private static final String MESSAGE_SUCCESS = "success";
	private static final String MESSAGE_NOANSWER = "请填写答案！";
	private static final String RESULT_MESSAGE_SAME = "Same";
	private static final String RESULT_MESSAGE_DIFFERENT = "Different";
	private static final Integer STATUS_0 = 0;
	private static final Integer STATUS_1 = 1;
	private static final Integer STATUS_2 = 2;
	
	@Test 
	public void testSelectByPrimaryKey() {
		System.out.println(answerService.selectByPrimaryKey(1));
	}
	@Test
	public void randomlist() 
	{
		List<Integer> allid = answerService.selectAllid();
		@SuppressWarnings("unchecked")
		List<Integer> list = RandomList.createRandomList(allid, 5);
		for(Integer i:list) 
		{
			System.out.println(i);
		}
	}
	@Test
	public void alllist() 
	{
		List<AnswerIdTitle> allidwithtitle = answerService.selectAllIdwithTitle();
		for(AnswerIdTitle i:allidwithtitle) 
		{
			System.out.println(i.getId());
			System.out.println(i.getQuestion());
		}
	}
	@Test
	public void addanswerhistory() {
			String sql = "select * from lilei;";
			Answer answer = answerService.selectByPrimaryKey(1);
			String result = ComparasionOfSqlUtils.SQLOfComparasion(answer.getSolution(),sql);
			Integer status = STATUS_0;
			switch(result) {
				case RESULT_MESSAGE_SAME:
					status = STATUS_1;
					break;
				case RESULT_MESSAGE_DIFFERENT:
					status = STATUS_0;
					break;
				default:
					status = STATUS_2;
					
			}
			Answerhistory answerhistory = new Answerhistory();
			Answerhistory replace = new Answerhistory();
			replace = answerHistoryService.selectByUserIdAndAnswerId(6,1);
			
			answerhistory.setUserId(6);
			answerhistory.setAnswerId(1);
			answerhistory.setUserAnswers(sql);
			answerhistory.setQuestionStatus(status);
			
			if(replace == null) {
				answerHistoryService.insert(answerhistory);
			}else {
				answerhistory.setId(replace.getId());
				answerHistoryService.updateByPrimaryKey(answerhistory);
			}
	}
	@Test
	public void getRole() {
		User auser = userService.selectByUsername("172101040");
		System.out.println("Id"+auser.getId());
		System.out.println("RoleId"+userRoleService.selectByUserId(auser.getId()).getRoleId());
		System.out.println("RoleName"+roleService.selectByPrimaryKey(userRoleService.selectByUserId(auser.getId()).getRoleId()).getName());
	}
}
