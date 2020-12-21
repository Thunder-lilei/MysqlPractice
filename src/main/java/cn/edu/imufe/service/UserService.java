package cn.edu.imufe.service;

import cn.edu.imufe.po.User;
import cn.edu.imufe.pojo.UserBaseInfoPojo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
	User selectByUsername(String username);

	Boolean selectByUsernameWithoutId(User user);

	List<UserBaseInfoPojo> getAllUserBaseInfo();

	PageInfo<?> getAllUserBaseInfo(int page, int pageSize);

	int updatePasswordByUsernameSelective(User record);

	Integer addUser(User user);

	Integer updateUser(User user);

	User getUserById(Long id);
}
