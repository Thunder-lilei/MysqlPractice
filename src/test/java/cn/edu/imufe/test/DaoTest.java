package cn.edu.imufe.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.edu.imufe.dao.AnswerDao;
import cn.edu.imufe.dao.AnswerhistoryDao;
import cn.edu.imufe.dao.AuserDao;
import cn.edu.imufe.dao.CourseDao;
import cn.edu.imufe.dao.JDao;
import cn.edu.imufe.dao.PDao;
import cn.edu.imufe.dao.SDao;
import cn.edu.imufe.dao.ScDao;
import cn.edu.imufe.dao.SpjDao;
import cn.edu.imufe.dao.StudentDao;
import cn.edu.imufe.entity.Auser;

public class DaoTest extends BaseTest{
	@Autowired
	private AuserDao auserDao;
	@Autowired
	private AnswerDao answerdao;
	@Autowired
	private AnswerhistoryDao AhD;
	@Autowired
	private CourseDao CD;
	@Autowired
	private JDao JD;
	@Autowired
	private PDao PD;
	@Autowired
	private ScDao SD;
	@Autowired
	private SDao sDA;
	@Autowired
	private SpjDao SPJD;
	@Autowired
	private StudentDao stuD;
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
		int id=1;
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
}
