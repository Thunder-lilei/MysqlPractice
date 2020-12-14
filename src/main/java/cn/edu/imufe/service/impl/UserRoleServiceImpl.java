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
	@Autowired
	private UserRoleMapper userRoleDao;
	@Autowired
	private RoleMapper roleDao;

	@Override
	public UserRole selectByUserId(Long id) {
		// TODO Auto-generated method stub
		return userRoleDao.selectByUserId(id);
	}

	@Override
	public String getRole(Long id) {
		return roleDao.selectByPrimaryKey(userRoleDao.selectByUserId(id).getRoleId()).getName();
	}

}
