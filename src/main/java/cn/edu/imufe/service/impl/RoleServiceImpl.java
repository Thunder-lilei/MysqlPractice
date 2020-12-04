package cn.edu.imufe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.imufe.dao.RoleDao;
import cn.edu.imufe.entity.Role;
import cn.edu.imufe.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleDao;

	@Override
	public Role selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return roleDao.selectByPrimaryKey(id);
	}
}
