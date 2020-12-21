package cn.edu.imufe.service.impl;

import cn.edu.imufe.dao.UserMapper;
import cn.edu.imufe.po.Paper;
import cn.edu.imufe.po.User;
import cn.edu.imufe.pojo.UserBaseInfoPojo;
import cn.edu.imufe.util.BCrypt;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.imufe.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	private final UserMapper userDao;
	@Autowired
	public UserServiceImpl(UserMapper userDao) {
		this.userDao = userDao;
	}

	@Override
	public User selectByUsername(String username) {
		return userDao.selectByUsername(username);
	}

	@Override
	public Boolean selectByUsernameWithoutId(User user) {
		if (userDao.selectByUsernameWithoutId(user) != null) {return true;}
		return false;
	}

	@Override
	public List<UserBaseInfoPojo> getAllUserBaseInfo() {
		return userDao.getAllUserBaseInfo();
	}

	/*
	 * @Author 李雷
	 * @Description
	 * 利用PageHelper插件实现用户基本信息分页查询
	 * @CreateDate 15:42 2020/12/21
	 * @UpdateDate 15:42 2020/12/21
	 * @Param [page, pageSize]
	 * @return com.github.pagehelper.PageInfo<?>
	 **/
	@Override
	public PageInfo<?> getAllUserBaseInfo(int page, int pageSize) {
		PageHelper.startPage(page,pageSize);
		List<UserBaseInfoPojo> classBaseInfoPojoList = getAllUserBaseInfo();
		return new PageInfo<>(classBaseInfoPojoList);
	}

	@Override
	public int updatePasswordByUsernameSelective(User record) {
		return userDao.updatePasswordByUsernameSelective(record);
	}

	/*
	 * @Author 李雷
	 * @Description
	 * 添加用户
	 * 不允许重名
	 * 用户密码加密
	 * @CreateDate 15:37 2020/12/16
	 * @UpdateDate 15:37 2020/12/16
	 * @Param [user]
	 * @return java.lang.Integer
	 **/
	@Override
	public Integer addUser(User user) {
		if (selectByUsername(user.getUsername()) != null) {return 0;}
		user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
		return userDao.insertSelective(user);
	}

	/*
	 * @Author 李雷
	 * @Description
	 * 更新用户信息
	 * 不能重名 更换密码重新加密
	 * @CreateDate 16:27 2020/12/16
	 * @UpdateDate 16:27 2020/12/16
	 * @Param [user]
	 * @return java.lang.Integer
	 **/
	@Override
	public Integer updateUser(User user) {
		if (selectByUsernameWithoutId(user)) {return 0;}
		User passwordUser = selectByUsername(user.getUsername());
		if (!user.getPassword().equals(passwordUser.getPassword())) {
			user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
		}
		return userDao.updateByPrimaryKeySelective(user);
	}

	@Override
	public User getUserById(Long id) {
		return userDao.selectByPrimaryKey(id);
	}

}
