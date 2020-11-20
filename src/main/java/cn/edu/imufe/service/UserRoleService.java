package cn.edu.imufe.service;

import java.util.List;

import cn.edu.imufe.entity.UserRole;

public interface UserRoleService {
	UserRole selectByUserId(Integer id);
}
