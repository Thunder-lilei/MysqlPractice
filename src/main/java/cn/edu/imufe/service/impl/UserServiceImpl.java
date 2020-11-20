package cn.edu.imufe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.imufe.dao.UserDao;
import cn.edu.imufe.entity.User;
import cn.edu.imufe.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userdao;

	@Override
	public User selectByUsername(String username) {
		// TODO Auto-generated method stub
		return userdao.selectByUsername(username);
	}

	@Override
	public int updatePasswordByUsernameSelective(User record) {
		// TODO Auto-generated method stub
		return userdao.updatePasswordByUsernameSelective(record);
	}

}
