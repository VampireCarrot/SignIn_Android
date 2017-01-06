package com.lwd.signin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.isnc.facesdk.SuperID;
import com.isnc.facesdk.common.Cache;
import com.isnc.facesdk.common.SDKConfig;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SuperID.initFaceSDK(this);
        SuperID.setDebugMode(true);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case SDKConfig.AUTH_SUCCESS:
                //<!-- openid 为开发者应用生成的openid，若调用faceLogin(Context context)进行注册授权，则系统将会自动生成一个openid -->
                String openid = Cache.getCached(this, SDKConfig.KEY_OPENID);
                // < !-- userInfo 为SuperID用户信息，格式为json -->
                String userInfo = Cache.getCached(this, SDKConfig.KEY_APPINFO);
                Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
                break;
            case SDKConfig.LOGINSUCCESS:
                //<!-- openid 为开发者应用的openid，若用户在调用faceLogin(Context context)进行注册授权，则系统将会自动生成一个openid，重新登录成功时返回此openid -->
                //    String openid = Cache.getCached(this, SDKConfig.KEY_OPENID);
                // <!-- userInfo 为SuperID用户信息，格式为json -->
                //    String userInfo = Cache.getCached(this, SDKConfig.KEY_APPINFO);
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(this, "登录失败", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SuperID.faceLogout(this);
    }
}
