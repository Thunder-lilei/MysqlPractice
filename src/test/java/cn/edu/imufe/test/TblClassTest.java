package cn.edu.imufe.test;

import cn.edu.imufe.po.TblClass;
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
public class TblClassTest extends BaseTest{
    @Autowired
    ClassService classService;
    @Test
    public void getAllClassBaseInfo() {
        List<ClassBaseInfoPojo> baseInfoPojoList = classService.getAllClassBaseInfo();
        baseInfoPojoList.forEach(temp-> System.out.println(temp.getClassName()));
    }
    @Test
    public void addClass() {
        TblClass c = new TblClass();
        c.setClassName("17计算机科学与技术2班");
        System.out.println(classService.addClass(c));
    }
    @Test
    public void selectByClassNameWithoutId() {
        TblClass c = new TblClass();
        c.setClassName("17软件工程2班");
        System.out.println(classService.selectByClassNameWithoutId(c));
    }
    @Test
    public void getClassById() {
        System.out.println(classService.getClass(1L));
    }
}
