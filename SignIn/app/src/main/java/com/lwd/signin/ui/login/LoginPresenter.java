package com.lwd.signin.ui.login;

import com.lwd.signin.api.SimpleMyCallBack;
import com.lwd.signin.base.BaseCommonPresenter;
import com.lwd.signin.model.HttpExceptionBean;
import com.lwd.signin.model.RequestBean.LoginParams;
import com.lwd.signin.model.ResultBean.Login;

import rx.Subscription;

/**
 * User: ZRJ
 * Date: 2016/12/1
 * Email: 471564517@qq.com
 * Description:
 */
public class LoginPresenter extends BaseCommonPresenter<LoginContract.View> implements LoginContract.Presenter {

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(LoginParams loginParams) {
        Subscription subscription = apiImple.getdoLogin("123","123").subscribe(newMySubscriber(new SimpleMyCallBack<Login>() {
            @Override
            public void onError(HttpExceptionBean mHttpExceptionBean) {
                super.onError(mHttpExceptionBean);
            }

            @Override
            public void onNext(Login login) {
                System.out.println(login.toString());
                view.loginSuccess(login);
            }
        }));
        mCompositeSubscription.add(subscription);

    }
}
