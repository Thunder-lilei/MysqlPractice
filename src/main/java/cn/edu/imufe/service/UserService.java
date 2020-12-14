package cn.edu.imufe.service;

import cn.edu.imufe.po.User;

public interface UserService {
	User selectByUsername(String username);
	
	int updatePasswordByUsernameSelective(User record);
}
