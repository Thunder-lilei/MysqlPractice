package cn.edu.imufe.dao;

import cn.edu.imufe.entity.S;
import cn.edu.imufe.entity.SExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SDao {
    int countByExample(SExample example);

    int deleteByExample(SExample example);

    int insert(S record);

    int insertSelective(S record);

    List<S> selectByExample(SExample example);

    int updateByExampleSelective(@Param("record") S record, @Param("example") SExample example);

    int updateByExample(@Param("record") S record, @Param("example") SExample example);
}