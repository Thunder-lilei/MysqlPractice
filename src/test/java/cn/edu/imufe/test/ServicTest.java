package cn.edu.imufe.test;

import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.imufe.entity.Answer;
import cn.edu.imufe.entity.Auser;
import cn.edu.imufe.pojo.Answer_id_title;
import cn.edu.imufe.service.*;
import cn.edu.imufe.util.RandomList;

public class ServicTest extends BaseTest{
	@Autowired
	private AuserService user;
	@Autowired
	private AnswerService answerdao;
	
	@Test
	public void testSelectAllUser() {
		List<Auser> userlist=user.SelectAllUser();
		System.out.println(userlist);
	}
	@Test 
	public void testSelectByPrimaryKey() {
		System.out.println(answerdao.selectByPrimaryKey(1));
	}
	@Test
	public void randomlist() 
	{
		List<Integer> allid = answerdao.selectAllid();
		List<Integer> list = RandomList.createRandomList(allid, 5);
		for(Integer i:list) 
		{
			System.out.println(i);
		}
	}
	@Test
	public void alllist() 
	{
		List<Answer_id_title> allidwithtitle = answerdao.selectAllIdwithTitle();
		for(Answer_id_title i:allidwithtitle) 
		{
			System.out.println(i.getId());
			System.out.println(i.getQuestion());
		}
	}
}
