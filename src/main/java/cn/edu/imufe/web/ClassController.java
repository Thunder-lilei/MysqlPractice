package cn.edu.imufe.web;

import cn.edu.imufe.po.TblClass;
import cn.edu.imufe.pojo.ClassBaseInfoPojo;
import cn.edu.imufe.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h3>MysqlPractice</h3>
 * <p>班级</p>
 *
 * @author : 李雷
 * @date : 2020-12-17 08:58
 **/
@Controller
@RequestMapping(value="/class")
public class ClassController extends BaseController{
    private final ClassService classService;
    @Autowired
    public ClassController(ClassService classService) {
        this.classService = classService;
    }
    /*
     * @Author 李雷
     * @Description
     * 获取所有班级基本信息id className
     * @CreateDate 9:13 2020/12/17
     * @UpdateDate 9:13 2020/12/17
     * @Param []
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/getAllClassBaseInfo", method = RequestMethod.POST)
    public Map<String,Object> getAllClassBaseInfo() {
        Map<String,Object> modelMap=new HashMap<>();
        List<ClassBaseInfoPojo> baseInfoPojoList = classService.getAllClassBaseInfo();
        if (baseInfoPojoList == null) {
            modelMap.put("message","暂时没有班级！");
        }
        modelMap.put("ClassBaseInfoList",baseInfoPojoList);
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 增加班级
     * 不允许重名
     * @CreateDate 9:28 2020/12/17
     * @UpdateDate 2020-12-17 09:33:06
     * @Param [c]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/addClass", method = RequestMethod.POST)
    public Map<String,Object> addClass(TblClass c) {
        Map<String,Object> modelMap=new HashMap<>();
        if (classService.addClass(c).equals(0)) {
            modelMap.put("message","请尝试更换班级名！");
            return modelMap;
        }
        modelMap.put("message","添加成功！");
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 更新班级信息
     * 需要班级id
     * @CreateDate 9:39 2020/12/17
     * @UpdateDate 9:39 2020/12/17
     * @Param [c]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/updateClass", method = RequestMethod.POST)
    public Map<String,Object> updateClass(TblClass c) {
        Map<String,Object> modelMap=new HashMap<>();
        if (classService.updateClass(c).equals(0)) {
            modelMap.put("message","请尝试更换班级名！");
            return modelMap;
        }
        modelMap.put("message","修改完成！");
        return modelMap;
    }
    /*
     * @Author 李雷
     * @Description
     * 删除班级
     * @CreateDate 9:39 2020/12/17
     * @UpdateDate 9:39 2020/12/17
     * @Param [id]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    @ResponseBody
    @RequestMapping(value = "/deleteClass", method = RequestMethod.POST)
    public Map<String,Object> deleteClass(@RequestParam Long id) {
        Map<String,Object> modelMap=new HashMap<>();
        if (classService.deleteClass(id).equals(0)) {
            modelMap.put("message","删除失败！");
            return modelMap;
        }
        modelMap.put("message","删除成功！");
        return modelMap;
    }
    @ResponseBody
    @RequestMapping(value = "/getClass", method = RequestMethod.POST)
    public Map<String,Object> getClass(@RequestParam Long id) {
        Map<String,Object> modelMap=new HashMap<>();
        TblClass c = classService.getClass(id);
        modelMap.put("class",c);
        if (c != null) {
            return modelMap;
        }
        modelMap.put("message","查询失败！");
        return modelMap;
    }
}