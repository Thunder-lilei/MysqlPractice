package cn.edu.imufe.service.impl;

import cn.edu.imufe.dao.RoleMapper;
import cn.edu.imufe.po.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.imufe.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	private final RoleMapper roleDao;
	@Autowired
	public RoleServiceImpl(RoleMapper roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public Role selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return roleDao.selectByPrimaryKey(id);
	}
}
