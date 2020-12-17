package cn.edu.imufe.service.impl;

import cn.edu.imufe.dao.ClassMapper;
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
    public List<ClassBaseInfoPojo> getAllClassBaseInfo() {
        return classMapper.getAllClassBaseInfo();
    }
}
