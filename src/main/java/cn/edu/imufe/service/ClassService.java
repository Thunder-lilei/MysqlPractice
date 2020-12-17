package cn.edu.imufe.service;

import cn.edu.imufe.po.Class;
import cn.edu.imufe.pojo.ClassBaseInfoPojo;

import javax.print.DocFlavor;
import java.util.List;

/**
 * <h3>MysqlPractice</h3>
 * <p>班级接口</p>
 *
 * @author : 李雷
 * @date : 2020-12-17 08:58
 **/
public interface ClassService {
    Class selectByClassName(String className);

    Boolean selectByClassNameWithoutId(Class c);

    List<ClassBaseInfoPojo> getAllClassBaseInfo();

    Class getClass(Long id);

    Integer addClass(Class c);

    Integer updateClass(Class c);

    Integer deleteClass(Long id);
}
