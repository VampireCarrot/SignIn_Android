package com.lwd.signin.ui.welcome;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.LinearLayout;

import com.lwd.signin.R;
import com.lwd.signin.base.BaseActivity;
import com.lwd.signin.model.ResultBean.Login;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * User: LWD
 * Date: 2017/1/11
 * Email: 13102169005@163.com
 * Description:欢迎页面
 */

public class WelcomeActivity extends BaseActivity<WelcomeContract.Presenter> implements WelcomeContract.View {
    @Bind(R.id.ll_content)
    LinearLayout llContent;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        initApi();
        this.createPresenter(new WelcomePresenter(this));

    }

    @Override
    public void loginSuccess(Login login) {

    }

    @Override
    public void hideLoading() {

    }
}
