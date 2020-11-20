package cn.edu.imufe.service;

import cn.edu.imufe.entity.User;

public interface UserService {
	User selectByUsername(String username);
	
	int updatePasswordByUsernameSelective(User record);
}
