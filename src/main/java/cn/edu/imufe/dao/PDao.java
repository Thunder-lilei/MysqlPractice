package cn.edu.imufe.dao;

import cn.edu.imufe.entity.P;
import cn.edu.imufe.entity.PExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PDao {
    int countByExample(PExample example);

    int deleteByExample(PExample example);

    int insert(P record);

    int insertSelective(P record);

    List<P> selectByExample(PExample example);

    int updateByExampleSelective(@Param("record") P record, @Param("example") PExample example);

    int updateByExample(@Param("record") P record, @Param("example") PExample example);
}