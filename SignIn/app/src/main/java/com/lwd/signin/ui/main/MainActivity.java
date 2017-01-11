package com.lwd.signin.ui.main;

import android.os.Bundle;

import com.lwd.signin.R;
import com.lwd.signin.base.BaseActivity;
import com.lwd.signin.model.ResultBean.Login;

import butterknife.ButterKnife;

/**
 * User: LWD
 * Date: 2017/1/11
 * Email: 13102169005@163.com
 * Description:
 */

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initApi();
        this.createPresenter(new MainPresenter(this));


    }

    @Override
    public void loginSuccess(Login login) {

    }

    @Override
    public void hideLoading() {

    }
}
