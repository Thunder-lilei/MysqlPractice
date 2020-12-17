package cn.edu.imufe.pojo;

/**
 * <h3>MysqlPractice</h3>
 * <p>班级基本信息</p>
 *
 * @author : 李雷
 * @date : 2020-12-17 09:00
 **/
public class ClassBaseInfoPojo {
    private Long id;

    private String className;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
