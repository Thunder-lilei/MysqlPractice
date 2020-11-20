package cn.edu.imufe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.imufe.dao.RoleDao;
import cn.edu.imufe.dao.UserRoleDao;
import cn.edu.imufe.service.RoleWithUserRoleService;

@Service
public class RoleWithUserRoleServiceImpl implements RoleWithUserRoleService {
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public String getRole(Integer id) {
		// TODO Auto-generated method stub
		return roleDao.selectByPrimaryKey(userRoleDao.selectByUserId(id).getRoleId()).getName();
	}
}
