package cn.edu.imufe.service.impl;

import cn.edu.imufe.dao.UserMapper;
import cn.edu.imufe.po.User;
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
		// TODO Auto-generated method stub
		return userDao.selectByUsername(username);
	}

	@Override
	public int updatePasswordByUsernameSelective(User record) {
		// TODO Auto-generated method stub
		return userDao.updatePasswordByUsernameSelective(record);
	}

}
