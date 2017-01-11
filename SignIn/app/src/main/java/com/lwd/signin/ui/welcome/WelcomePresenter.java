package com.lwd.signin.ui.welcome;

import com.lwd.signin.base.BaseCommonPresenter;
import com.lwd.signin.model.RequestBean.LoginParams;

/**
 * User: LWD
 * Date: 2017/1/11
 * Email: 13102169005@163.com
 * Description:
 */

public class WelcomePresenter extends BaseCommonPresenter<WelcomeContract.View> implements WelcomeContract.Presenter{
    public WelcomePresenter(WelcomeContract.View view) {
        super(view);
    }

    @Override
    public void login(LoginParams loginParams) {

    }
}
