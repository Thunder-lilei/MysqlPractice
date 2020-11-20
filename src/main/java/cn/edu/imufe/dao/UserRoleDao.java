package cn.edu.imufe.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.edu.imufe.entity.UserRole;
import cn.edu.imufe.entity.UserRoleExample;

public interface UserRoleDao {
    int countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExample(UserRoleExample example);
    
    UserRole selectByUserId(Integer id);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);
}