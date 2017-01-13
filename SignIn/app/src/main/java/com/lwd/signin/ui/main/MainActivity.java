package com.lwd.signin.ui.main;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.lwd.signin.R;
import com.lwd.signin.base.BaseActivity;
import com.lwd.signin.model.ResultBean.Login;
import com.lwd.signin.utils.LogUtil;
import com.lwd.signin.utils.ToastUtils;
import com.lwd.signin.utils.Utils;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionNo;
import com.yanzhenjie.permission.PermissionYes;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * User: LWD
 * Date: 2017/1/11
 * Email: 13102169005@163.com
 * Description:
 */

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View ,AMapLocationListener{


    @Bind(R.id.ll_left_menu)
    LinearLayout llLeftMenu;
    @Bind(R.id.tv_date)
    TextView tvDate;
    @Bind(R.id.tv_time)
    TextView tvTime;
    @Bind(R.id.tv_address)
    TextView tvAddress;
    @Bind(R.id.iv_map)
    MapView ivMap;
    @Bind(R.id.btn_signin)
    Button btnSignin;

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    //声明Amap类对象
    private AMap aMap;
    //声明静态常量
    private static final int PERMISSION_REQUEST_CODE = 101;
    private static final int PERMISSION_SETTING_CODE = 102;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initApi();
        this.createPresenter(new MainPresenter(this));
        initPermission();
        initLocation();
        initMap(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        ivMap.onSaveInstanceState(outState);
    }

    /**
     * 初始化APP权限
     */
    private void initPermission() {
        // 申请多个权限。
        AndPermission.with(this)
                .requestCode(PERMISSION_REQUEST_CODE)
                .permission(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
                        Manifest.permission.BLUETOOTH)
                // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框，避免用户勾选不再提示。
                .rationale((requestCode, rationale) ->
                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                        AndPermission.rationaleDialog(MainActivity.this, rationale).show()
                )
                .send();
    }

    /**
     * 初始化定位
     */
    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(2000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否强制刷新WIFI，默认为true，强制刷新。
        mLocationOption.setWifiActiveScan(false);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(30000);
        //给定位客户端对象设置定位参数
        mLocationOption.setOnceLocation(true);
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();

    }

    /**
     * 初始化高德地图
     * @param savedInstanceState
     */
    private void initMap(Bundle savedInstanceState) {
        ivMap.onCreate(savedInstanceState);// 此方法必须重写
        if (aMap == null) {
            aMap = ivMap.getMap();
        }
        UiSettings settings=aMap.getUiSettings();
        settings.setZoomControlsEnabled(false);
        settings.setCompassEnabled(true);
        settings.setScrollGesturesEnabled(false);
        settings.setZoomGesturesEnabled(false);
        settings.setTiltGesturesEnabled(false);
        settings.setRotateGesturesEnabled(false);
        settings.setAllGesturesEnabled (false);
    }
    @PermissionYes(PERMISSION_REQUEST_CODE)
    private void getMultiYes(List<String> grantedPermissions) {
        Toast.makeText(this, "获取联系人、短信权限成功", Toast.LENGTH_SHORT).show();
    }

    @PermissionNo(PERMISSION_REQUEST_CODE)
    private void getMultiNo(List<String> deniedPermissions) {
        Toast.makeText(this, "获取联系人、短信权限失败", Toast.LENGTH_SHORT).show();

        // 用户否勾选了不再提示并且拒绝了权限，那么提示用户到设置中授权。
        if (AndPermission.hasAlwaysDeniedPermission(this, deniedPermissions)) {
            AndPermission.defaultSettingDialog(this, PERMISSION_SETTING_CODE)
                    .setTitle("哎呀，没有权限")
                    .setMessage("你拒绝了我们的权限申请，真是不能愉快的玩耍了！")
                    .setPositiveButton("给你给你")
                    .setNegativeButton("就不给你", null)
                    .show();

            // 更多自定dialog，请看上面。
        }
    }



    @Override
    public void loginSuccess(Login login) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        ivMap.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        ivMap.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        ivMap.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PERMISSION_SETTING_CODE: {
                Toast.makeText(this, "用户从设置回来了", Toast.LENGTH_LONG).show();
                break;
            }
        }
    }

    @OnClick({R.id.ll_left_menu, R.id.btn_signin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_left_menu:
                ToastUtils.showShort("销毁了");
                break;
            case R.id.btn_signin:
                ToastUtils.showShort("我我我我");

                break;
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //可在其中解析amapLocation获取相应内容。
                //解析定位结果
                String result = Utils.getLocationStr(aMapLocation);
                LatLng latLng=  new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
                aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 18, 30, 30)));
                aMap.clear();
                aMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                LogUtil.e(result);
            }else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                LogUtil.e("AmapError","location Error, ErrCode:" + aMapLocation.getErrorCode() + ", errInfo:" + aMapLocation.getErrorInfo());
            }
        }
    }
}
