package cn.edu.imufe.dao;

import cn.edu.imufe.po.TblClass;
import cn.edu.imufe.po.TblClassExample;
import java.util.List;

import cn.edu.imufe.pojo.ClassBaseInfoPojo;
import org.apache.ibatis.annotations.Param;

public interface ClassMapper {
    long countByExample(TblClassExample example);

    int deleteByExample(TblClassExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TblClass record);

    int insertSelective(TblClass record);

    List<TblClass> selectByExample(TblClassExample example);

    TblClass selectByPrimaryKey(Long id);

    TblClass selectByClassNameWithoutId(TblClass c);

    TblClass selectByClassName(String className);

    List<ClassBaseInfoPojo> getAllClassBaseInfo();

    int updateByExampleSelective(@Param("record") TblClass record, @Param("example") TblClassExample example);

    int updateByExample(@Param("record") TblClass record, @Param("example") TblClassExample example);

    int updateByPrimaryKeySelective(TblClass record);

    int updateByPrimaryKey(TblClass record);
}