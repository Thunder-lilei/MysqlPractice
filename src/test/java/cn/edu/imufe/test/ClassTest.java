package cn.edu.imufe.test;

import cn.edu.imufe.pojo.ClassBaseInfoPojo;
import cn.edu.imufe.service.ClassService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <h3>MysqlPractice</h3>
 * <p>班级测试</p>
 *
 * @author : 李雷
 * @date : 2020-12-17 09:02
 **/
public class ClassTest extends BaseTest{
    @Autowired
    ClassService classService;
    @Test
    public void getAllClassBaseInfo() {
        List<ClassBaseInfoPojo> baseInfoPojoList = classService.getAllClassBaseInfo();
        baseInfoPojoList.forEach(temp-> System.out.println(temp.getClassName()));
    }
}
