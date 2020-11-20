package cn.edu.imufe.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import cn.edu.imufe.entity.Answer;
import cn.edu.imufe.entity.Answerhistory;
import cn.edu.imufe.entity.User;
import cn.edu.imufe.pojo.AnswerIdTitle;
import cn.edu.imufe.service.*;
import cn.edu.imufe.util.RandomList;

public  class ServicTest extends BaseTest{
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private AnswerService answerservice;
	@Autowired
	private AnswerHistoryService answerhistoryservice;
	
	@Test 
	public void testSelectByPrimaryKey() {
		System.out.println(answerservice.selectByPrimaryKey(1));
	}
	@Test
	public void randomlist() 
	{
		List<Integer> allid = answerservice.selectAllid();
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
		List<AnswerIdTitle> allidwithtitle = answerservice.selectAllIdwithTitle();
		for(AnswerIdTitle i:allidwithtitle) 
		{
			System.out.println(i.getId());
			System.out.println(i.getQuestion());
		}
	}
	@Test
	public void addanswerhistory() {
		Answerhistory answerhistory = new Answerhistory();
		//查找对应问题
		Answer answer = answerservice.selectByPrimaryKey(Integer.parseInt("1"));
				
		//添加数据
		answerhistory.setUsername("lilei");
		answerhistory.setAnswerId(3);
		answerhistory.setUserAnswers(answer.getSolution());
		answerhistory.setQuestionStatus(Integer.parseInt("2"));
		//插入
		System.out.println(answerhistoryservice.insert(answerhistory));
	}
	@Test
	public void getRole() {
		User auser = userService.selectByUsername("172101040");
		System.out.println("Id"+auser.getId());
		System.out.println("RoleId"+userRoleService.selectByUserId(auser.getId()).getRoleId());
		System.out.println("RoleName"+roleService.selectByPrimaryKey(userRoleService.selectByUserId(auser.getId()).getRoleId()).getName());
	}
}
