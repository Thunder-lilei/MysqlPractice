package cn.edu.imufe.dao;

import cn.edu.imufe.po.Class;
import cn.edu.imufe.po.ClassExample;
import java.util.List;

import cn.edu.imufe.pojo.ClassBaseInfoPojo;
import org.apache.ibatis.annotations.Param;

public interface ClassMapper {
    long countByExample(ClassExample example);

    int deleteByExample(ClassExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Class record);

    int insertSelective(Class record);

    List<Class> selectByExample(ClassExample example);

    Class selectByPrimaryKey(Long id);

    Class selectByClassNameWithoutId(Class c);

    Class selectByClassName(String className);

    List<ClassBaseInfoPojo> getAllClassBaseInfo();

    int updateByExampleSelective(@Param("record") Class record, @Param("example") ClassExample example);

    int updateByExample(@Param("record") Class record, @Param("example") ClassExample example);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);
}