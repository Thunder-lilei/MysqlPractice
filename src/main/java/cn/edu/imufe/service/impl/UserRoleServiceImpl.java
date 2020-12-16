package cn.edu.imufe.service.impl;


import cn.edu.imufe.dao.RoleMapper;
import cn.edu.imufe.dao.UserRoleMapper;
import cn.edu.imufe.po.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.imufe.service.UserRoleService;


/**
 * @author lilei
 *
 * 2020年11月20日
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
	private final UserRoleMapper userRoleDao;
	private final RoleMapper roleDao;
	@Autowired
	public UserRoleServiceImpl(UserRoleMapper userRoleDao, RoleMapper roleDao) {
		this.userRoleDao = userRoleDao;
		this.roleDao = roleDao;
	}

	@Override
	public UserRole selectByUserId(Long id) {
		return userRoleDao.selectByUserId(id);
	}

	@Override
	public String getRole(Long id) {
		return roleDao.selectByPrimaryKey(userRoleDao.selectByUserId(id).getRoleId()).getName();
	}

	/*
	 * @Author 李雷
	 * @Description
	 * 用户授权
	 * 不能重复添加
	 * @CreateDate 15:46 2020/12/16
	 * @UpdateDate 15:46 2020/12/16
	 * @Param [userId, roleId]
	 * @return java.lang.Integer
	 **/
	@Override
	public Integer addUserRole(Long userId, Long roleId) {
		UserRole userRole = userRoleDao.selectByUserId(userId);
		if (userRole != null && userRole.getRoleId().equals(roleId)) {
			return 0;
		}
		userRole = new UserRole();
		userRole.setUserId(userId);
		userRole.setRoleId(roleId);
		return userRoleDao.insertSelective(userRole);
	}

}
