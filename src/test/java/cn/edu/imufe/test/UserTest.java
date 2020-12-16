package cn.edu.imufe.test;

import cn.edu.imufe.po.User;
import cn.edu.imufe.pojo.UserBaseInfoPojo;
import cn.edu.imufe.service.UserRoleService;
import cn.edu.imufe.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * <h3>MysqlPractice</h3>
 * <p></p>
 *
 * @author : 李雷
 * @date : 2020-12-16 15:38
 **/
public class UserTest extends BaseTest{
    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;
    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("lilei");
        user.setPassword("lilei");
        user.setNickname("李雷");
        System.out.println(userService.addUser(user));
    }
    @Test
    public void addUserRole() {
        System.out.println(userRoleService.addUserRole(7L,1L));
    }
    @Test
    public void selectByUsernameWithoutId() {
        User user = userService.selectByUsername("lilei");
        System.out.println(userService.selectByUsernameWithoutId(user));
    }
    @Test
    public void getAllUserBaseInfo() {
        List<UserBaseInfoPojo> userBaseInfoPojos = userService.getAllUserBaseInfo();
        userBaseInfoPojos.forEach(temp-> System.out.println(temp.getId()+" "+temp.getNickname()));
    }
    @Test
    public void getUserById() {
        System.out.println(userService.getUserById(1L));
    }

}
