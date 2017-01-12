package com.lwd.signin.ui.main;

import com.lwd.signin.base.BasePresenter;
import com.lwd.signin.base.BaseView;
import com.lwd.signin.model.RequestBean.LoginParams;
import com.lwd.signin.model.ResultBean.Login;

/**
 * User: LWD
 * Date: 2017/1/11
 * Email: 13102169005@163.com
 * Description:
 */

public interface MainContract {
    interface View extends BaseView {
        void loginSuccess(Login login);

    }

    interface Presenter extends BasePresenter {
        void login(LoginParams loginParams);
    }
}
