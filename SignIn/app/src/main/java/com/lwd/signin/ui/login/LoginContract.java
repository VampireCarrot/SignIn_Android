package com.lwd.signin.ui.login;


import com.lwd.signin.base.BasePresenter;
import com.lwd.signin.base.BaseView;
import com.lwd.signin.model.RequestBean.LoginParams;
import com.lwd.signin.model.ResultBean.Login;

/**
 * User: ZRJ
 * Date: 2016/12/1
 * Email: 471564517@qq.com
 * Description:
 */
public interface LoginContract {
    interface View extends BaseView {
        void loginSuccess(Login login);
    }

    interface Presenter extends BasePresenter {
        void login(LoginParams loginParams);
    }

}
