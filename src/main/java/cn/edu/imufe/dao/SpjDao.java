package cn.edu.imufe.dao;

import cn.edu.imufe.entity.Spj;
import cn.edu.imufe.entity.SpjExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SpjDao {
    int countByExample(SpjExample example);

    int deleteByExample(SpjExample example);

    int insert(Spj record);

    int insertSelective(Spj record);

    List<Spj> selectByExample(SpjExample example);

    int updateByExampleSelective(@Param("record") Spj record, @Param("example") SpjExample example);

    int updateByExample(@Param("record") Spj record, @Param("example") SpjExample example);
}