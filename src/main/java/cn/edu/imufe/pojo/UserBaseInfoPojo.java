package cn.edu.imufe.pojo;

/**
 * <h3>MysqlPractice</h3>
 * <p>包含用户id和nickname</p>
 *
 * @author : 李雷
 * @date : 2020-12-16 16:38
 **/
public class UserBaseInfoPojo {
    private Long id;

    private String nickname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
