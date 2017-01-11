package com.lwd.signin.ui.main;

import com.lwd.signin.base.BaseCommonPresenter;
import com.lwd.signin.model.RequestBean.LoginParams;

/**
 * User: LWD
 * Date: 2017/1/11
 * Email: 13102169005@163.com
 * Description:
 */

public class MainPresenter extends BaseCommonPresenter<MainContract.View> implements MainContract.Presenter {
    public MainPresenter(MainContract.View view) {
        super(view);
    }

    @Override
    public void login(LoginParams loginParams) {

    }
}
