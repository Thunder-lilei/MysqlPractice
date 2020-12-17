package cn.edu.imufe.service;

import cn.edu.imufe.pojo.ClassBaseInfoPojo;

import java.util.List;

/**
 * <h3>MysqlPractice</h3>
 * <p>班级接口</p>
 *
 * @author : 李雷
 * @date : 2020-12-17 08:58
 **/
public interface ClassService {
    List<ClassBaseInfoPojo> getAllClassBaseInfo();
}
