package cn.edu.imufe;

import cn.edu.imufe.util.BCrypt;
import org.junit.Test;

/**
 * <h3>MysqlPractice</h3>
 * <p></p>
 *
 * @author : 李雷
 * @date : 2020-12-10 15:25
 **/
public class UtilTest {
    @Test
    public void BCryptTest() {
        String pwd = "lilei";
        String pwdB = BCrypt.hashpw(pwd,BCrypt.gensalt());
        System.out.println(pwdB);
        System.out.println(BCrypt.checkpw(pwd,pwdB));
    }
}
