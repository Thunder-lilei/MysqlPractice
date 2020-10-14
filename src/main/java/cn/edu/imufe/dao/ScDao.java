package cn.edu.imufe.dao;

import cn.edu.imufe.entity.Sc;
import cn.edu.imufe.entity.ScExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScDao {
    int countByExample(ScExample example);

    int deleteByExample(ScExample example);

    int insert(Sc record);

    int insertSelective(Sc record);

    List<Sc> selectByExample(ScExample example);

    int updateByExampleSelective(@Param("record") Sc record, @Param("example") ScExample example);

    int updateByExample(@Param("record") Sc record, @Param("example") ScExample example);
}