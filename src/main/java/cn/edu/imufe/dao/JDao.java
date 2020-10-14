package cn.edu.imufe.dao;

import cn.edu.imufe.entity.J;
import cn.edu.imufe.entity.JExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JDao {
    int countByExample(JExample example);

    int deleteByExample(JExample example);

    int insert(J record);

    int insertSelective(J record);

    List<J> selectByExample(JExample example);

    int updateByExampleSelective(@Param("record") J record, @Param("example") JExample example);

    int updateByExample(@Param("record") J record, @Param("example") JExample example);
}