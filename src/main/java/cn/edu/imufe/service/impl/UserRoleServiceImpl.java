package cn.edu.imufe.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.imufe.dao.UserRoleDao;
import cn.edu.imufe.entity.UserRole;
import cn.edu.imufe.service.UserRoleService;


/**
 * @author lilei
 *
 * 2020年11月20日
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public UserRole selectByUserId(Integer id) {
		// TODO Auto-generated method stub
		return userRoleDao.selectByUserId(id);
	}

}
