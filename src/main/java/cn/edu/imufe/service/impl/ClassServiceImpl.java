package cn.edu.imufe.service.impl;

import cn.edu.imufe.dao.ClassMapper;
import cn.edu.imufe.po.TblClass;
import cn.edu.imufe.pojo.ClassBaseInfoPojo;
import cn.edu.imufe.service.ClassService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public TblClass selectByClassName(String className) {
        return classMapper.selectByClassName(className);
    }

    @Override
    public Boolean selectByClassNameWithoutId(TblClass c) {
        if (classMapper.selectByClassNameWithoutId(c) != null) {return true;}
        return false;
    }

    @Override
    public List<ClassBaseInfoPojo> getAllClassBaseInfo() {
        return classMapper.getAllClassBaseInfo();
    }

    /*
     * @Author 李雷
     * @Description
     * 利用PageHelper插件实现班级基本信息分页查询
     * @CreateDate 15:41 2020/12/21
     * @UpdateDate 15:41 2020/12/21
     * @Param [page, pageSize]
     * @return com.github.pagehelper.PageInfo<?>
     **/
    @Override
    public PageInfo<?> getAllClassBaseInfo(int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<ClassBaseInfoPojo> classBaseInfoPojoList = getAllClassBaseInfo();
        return new PageInfo<>(classBaseInfoPojoList);
    }

    @Override
    public TblClass getClass(Long id) {
        return classMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer addClass(TblClass c) {
        if (selectByClassName(c.getClassName()) != null) {return 0;}
        return classMapper.insertSelective(c);
    }

    @Override
    public Integer updateClass(TblClass c) {
        if (selectByClassNameWithoutId(c)) {return 0;}
        return classMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public Integer deleteClass(Long id) {
        return classMapper.deleteByPrimaryKey(id);
    }
}
