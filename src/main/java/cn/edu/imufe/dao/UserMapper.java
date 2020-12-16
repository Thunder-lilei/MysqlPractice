package cn.edu.imufe.dao;

import cn.edu.imufe.po.User;
import cn.edu.imufe.po.UserExample;
import java.util.List;

import cn.edu.imufe.pojo.UserBaseInfoPojo;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Long id);

    User selectByUsername(String username);

    User selectByUsernameWithoutId(User user);

    List<UserBaseInfoPojo> getAllUserBaseInfo();

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updatePasswordByUsernameSelective(User record);

    int updateByPrimaryKey(User record);
}