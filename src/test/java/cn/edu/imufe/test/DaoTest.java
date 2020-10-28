package cn.edu.imufe.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.imufe.dao.AnswerDao;
import cn.edu.imufe.dao.AnswerhistoryDao;
import cn.edu.imufe.dao.AuserDao;
import cn.edu.imufe.entity.Auser;

public class DaoTest extends BaseTest{
	@Autowired
	private AuserDao auserDao;
	@Autowired
	private AnswerDao answerdao;
	@Autowired
	private AnswerhistoryDao AhD;
	@Test
	public void SelectAllAuser() {
		List<Auser> auser = auserDao.SelectAllUser();
		System.out.println(auser);
	}
	@Test
	public void SelectAnswerByPrimarykey() {
		int id=1;
		System.out.println(answerdao.selectByPrimaryKey(id));
	}
	@Test
	public void SelectAnswerhistoryByPrimarykey() {
		System.out.println(AhD.selectByPrimaryKey(1));
	}
	@Test
	public void selectAllid() 
	{
		List<Integer> allid = answerdao.selectAllid();
		for(Integer i:allid) 
		{
			System.out.println(i);
		}
	}
	@Test
	public void login() 
	{
//		Auser auser = new Auser();
//		auser.setUsername("172101040");
//		auser.setPassword("lilei");
		Auser auser = auserDao.selectByUsername("172101040");
		if(auser!=null) 
		{
			if(auser.getPassword().equals("lilei")) 
			{
				System.out.println("yes");
			}else 
			{
				System.out.println("错误的密码");
			}
		}else 
		{
			System.out.println("错误的账号");
		}
	}
	@Test
	public void updatepassword()
	{
		Auser auser  = auserDao.selectByUsername("172101040");
		auser.setPassword("lilei");
		Integer result = auserDao.updatePasswordByUsernameSelective(auser);
		if(result.equals(1)) 
		{
			System.out.println("yes");
		}else 
		{
			System.out.println("no");
		}
	} 
}
