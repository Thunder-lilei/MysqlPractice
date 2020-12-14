package cn.edu.imufe.service;

import cn.edu.imufe.po.UserRole;

public interface UserRoleService {
	UserRole selectByUserId(Long id);

	String getRole(Long id);
}
