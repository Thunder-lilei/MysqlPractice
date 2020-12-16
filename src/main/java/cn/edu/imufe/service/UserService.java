package cn.edu.imufe.service;

import cn.edu.imufe.po.User;

public interface UserService {
	User selectByUsername(String username);

	Boolean selectByUsernameWithoutId(User user);
	
	int updatePasswordByUsernameSelective(User record);

	Integer addUser(User user);

	Integer updateUser(User user);
}
