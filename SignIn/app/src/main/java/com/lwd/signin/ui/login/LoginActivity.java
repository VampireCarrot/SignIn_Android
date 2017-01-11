package com.lwd.signin.ui.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.lwd.signin.R;
import com.lwd.signin.base.BaseActivity;
import com.lwd.signin.model.ResultBean.Login;
import com.lwd.signin.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * User: ZRJ
 * Date: 2016/12/1
 * Email: 471564517@qq.com
 * Description:
 */
public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {


    @Bind(R.id.edt_UserName)
    EditText edtUserName;
    @Bind(R.id.edt_Password)
    EditText edtPassword;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.ll_reigster)
    RelativeLayout llReigster;
    @Bind(R.id.ll_content)
    RelativeLayout llContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initApi();
        this.createPresenter(new LoginPresenter(this));


    }


    @Override
    public void hideLoading() {

    }

    @Override
    public void loginSuccess(Login login) {

        ToastUtils.showToast("登陆成功" + "返回数据：" + login.toString());
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }


    @OnClick({R.id.btn_login, R.id.ll_reigster, R.id.ll_content})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                break;
            case R.id.ll_reigster:
                break;
            case R.id.ll_content:
                break;
        }
    }
}
