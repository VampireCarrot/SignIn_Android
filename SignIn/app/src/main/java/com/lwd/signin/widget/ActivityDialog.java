package com.lwd.signin.widget;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lwd.signin.R;
import com.lwd.signin.utils.SpUtils;
import com.lwd.signin.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * User: LWD
 * Date: 2016/12/13
 * Email: 13102169005@163.com
 * Description:
 */

public class ActivityDialog extends Activity {


    @Bind(R.id.tv_kefu_phone)
    EditText tvKefuPhone;
    @Bind(R.id.btn_cancel_dialog)
    Button btnCancelDialog;
    @Bind(R.id.btn_confirm_dialog)
    Button btnConfirmDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_dialog);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        String Baseurl = (String) SpUtils.get(ActivityDialog.this,"BaseUrl","172.17.1.114:8080");
        tvKefuPhone.setText(Baseurl);

    }


    @OnClick({R.id.btn_cancel_dialog, R.id.btn_confirm_dialog})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel_dialog:
                finish();
                break;
            case R.id.btn_confirm_dialog:
                if (TextUtils.isEmpty(tvKefuPhone.getText().toString().trim())){
                    ToastUtils.showToast("请输入虚拟路径");
                }else {
                SpUtils.put(ActivityDialog.this,"BaseUrl",tvKefuPhone.getText().toString().trim());
                finish();}
                break;
        }
    }
}