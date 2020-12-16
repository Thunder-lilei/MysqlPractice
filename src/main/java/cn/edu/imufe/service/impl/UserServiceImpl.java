package cn.edu.imufe.service.impl;

import cn.edu.imufe.dao.UserMapper;
import cn.edu.imufe.po.User;
import cn.edu.imufe.util.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.imufe.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	private final UserMapper userDao;
	@Autowired
	public UserServiceImpl(UserMapper userdao) {
		this.userDao = userdao;
	}

	@Override
	public User selectByUsername(String username) {
		return userDao.selectByUsername(username);
	}

	@Override
	public int updatePasswordByUsernameSelective(User record) {
		return userDao.updatePasswordByUsernameSelective(record);
	}

	/*
	 * @Author 李雷
	 * @Description
	 * 添加用户
	 * 不允许重名
	 * 用户密码加密
	 * @CreateDate 15:37 2020/12/16
	 * @UpdateDate 15:37 2020/12/16
	 * @Param [user]
	 * @return java.lang.Integer
	 **/
	@Override
	public Integer addUser(User user) {
		if (selectByUsername(user.getUsername()) != null) {return 0;}
		user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
		return userDao.insertSelective(user);
	}

}
