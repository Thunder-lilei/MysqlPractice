package cn.edu.imufe.dao;

import cn.edu.imufe.entity.Auser;
import cn.edu.imufe.entity.AuserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AuserDao {
    int countByExample(AuserExample example);

    int deleteByExample(AuserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auser record);

    int insertSelective(Auser record);

    List<Auser> selectByExample(AuserExample example);
    
    List<Auser> SelectAllUser();
    
    Auser selectByPrimaryKey(Integer id);
    
    Auser selectByUsername(String username);

    int updateByExampleSelective(@Param("record") Auser record, @Param("example") AuserExample example);

    int updateByExample(@Param("record") Auser record, @Param("example") AuserExample example);

    int updateByPrimaryKeySelective(Auser record);

    int updateByPrimaryKey(Auser record);
}