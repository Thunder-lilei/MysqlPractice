package cn.edu.imufe.test;

import java.util.List;

import cn.edu.imufe.dao.AnswerHistoryMapper;
import cn.edu.imufe.dao.AnswerMapper;
import cn.edu.imufe.dao.UserMapper;
import cn.edu.imufe.po.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DaoTest extends BaseTest{
	@Autowired
	private UserMapper userDao;
	@Autowired
	private AnswerMapper answerDao;
	@Autowired
	private AnswerHistoryMapper AhD;
	@Test
	public void SelectAnswerByPrimarykey() {
		Long id=1L;
		System.out.println(answerDao.selectByPrimaryKey(id));
	}
	@Test
	public void SelectAnswerhistoryByPrimarykey() {
		System.out.println(AhD.selectByPrimaryKey(1L));
	}
	@Test
	public void selectAllid() 
	{
		List<Long> allid = answerDao.selectAllId();
		for(Long i:allid)
		{
			System.out.println(i);
		}
	}
	@Test
	public void login() 
	{
		User user = userDao.selectByUsername("172101040");
		if(user!=null)
		{
			if(user.getPassword().equals("lilei"))
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
		User user  = userDao.selectByUsername("172101040");
		user.setPassword("lilei");
		Integer result = userDao.updatePasswordByUsernameSelective(user);
		if(result.equals(1)) 
		{
			System.out.println("yes");
		}else 
		{
			System.out.println("no");
		}
	} 
}
