package cn.edu.imufe.service.impl;

import cn.edu.imufe.dao.ClassMapper;
import cn.edu.imufe.po.Class;
import cn.edu.imufe.pojo.ClassBaseInfoPojo;
import cn.edu.imufe.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <h3>MysqlPractice</h3>
 * <p>班级接口实现类</p>
 *
 * @author : 李雷
 * @date : 2020-12-17 08:59
 **/
@Service
public class ClassServiceImpl implements ClassService {
    private final ClassMapper classMapper;
    @Autowired
    public ClassServiceImpl(ClassMapper classMapper) {
        this.classMapper = classMapper;
    }

    @Override
    public Class selectByClassName(String className) {
        return classMapper.selectByClassName(className);
    }

    @Override
    public Boolean selectByClassNameWithoutId(Class c) {
        if (classMapper.selectByClassNameWithoutId(c) != null) {return true;}
        return false;
    }

    @Override
    public List<ClassBaseInfoPojo> getAllClassBaseInfo() {
        return classMapper.getAllClassBaseInfo();
    }

    @Override
    public Integer addClass(Class c) {
        if (selectByClassName(c.getClassName()) != null) {return 0;}
        return classMapper.insertSelective(c);
    }

    @Override
    public Integer updateClass(Class c) {
        if (selectByClassNameWithoutId(c)) {return 0;}
        return classMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public Integer deleteClass(Long id) {
        return classMapper.deleteByPrimaryKey(id);
    }
}
