package com.lwd.signin.model.ResultBean;

import java.io.Serializable;

/**
 * User: ZRJ
 * Date: 2016/11/30
 * Email: 471564517@qq.com
 * Description: 登陆实体类
 */
public class Login implements Serializable {

    /**
     * userId : 1
     * userName : 123
     * password : 123
     * phoneNum : 13102169006
     * nickname : NICK
     */

    private int userId;
    private String userName;
    private String password;
    private String phoneNum;
    private String nickname;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Login{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
