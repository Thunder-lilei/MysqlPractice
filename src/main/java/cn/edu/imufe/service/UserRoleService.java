package cn.edu.imufe.service;

import cn.edu.imufe.entity.UserRole;

public interface UserRoleService {
	UserRole selectByUserId(Integer id);
}
