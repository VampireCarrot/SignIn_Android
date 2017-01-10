package com.lwd.signin.model.RequestBean;

/**
 * 登录请求参数
 * Created by Android on 2015-12-23 023.
 */
public class LoginParams {

    public String userName;
    public String password;

    public LoginParams(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
