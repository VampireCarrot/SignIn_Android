package com.lwd.signin.ui.main;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * User: LWD
 * Date: 2017/1/11
 * Email: 13102169005@163.com
 * Description:
 */

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initApi();
        this.createPresenter(new MainPresenter(this));
        initLocation();
        ivMap.onCreate(savedInstanceState);// 此方法必须重写


    }

    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mAMapLocationListener);
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
    AMapLocationListener mAMapLocationListener = new AMapLocationListener(){
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {

                    //可在其中解析amapLocation获取相应内容。
                    //解析定位结果
                    String result = Utils.getLocationStr(amapLocation);
                    if (aMap == null) {
                        aMap = ivMap.getMap();
                    }
                    UiSettings uiSettings = aMap.getUiSettings();
                    LatLng latLng=  new LatLng(amapLocation.getLatitude(),amapLocation.getLongitude());
                    aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng, 18, 30, 30)));
                    aMap.clear();
                    uiSettings.setScaleControlsEnabled(false);
                    uiSettings.setZoomGesturesEnabled(false);
                    aMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                    LogUtil.e(result);
                }else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    LogUtil.e("AmapError","location Error, ErrCode:" + amapLocation.getErrorCode() + ", errInfo:" + amapLocation.getErrorInfo());
                }
            }
        }
    };
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

    @OnClick({R.id.ll_left_menu, R.id.btn_signin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_left_menu:
                ToastUtils.showShort("销毁了");
                break;
            case R.id.btn_signin:
                ToastUtils.showShort("点击了");
                //启动定位
                insertDummyContactWrapper();

                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        ivMap.onSaveInstanceState(outState);
    }

    /**
     * 判断当前权限是否允许,弹出提示框来选择
     */
    private void insertDummyContactWrapper() {
        // 需要验证的权限
        int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
            // 弹窗询问 ，让用户自己判断
            requestPermissions(new String[] {Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_COARSE_LOCATION}, 100011);
            return;
        }
    }
    /**
     * 用户进行权限设置后的回调函数 , 来响应用户的操作，无论用户是否同意权限，Activity都会
     * 执行此回调方法，所以我们可以把具体操作写在这里
     * @param requestCode
     * @param permissions
     * @param grantResults
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case 100011:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //这里写你需要相关权限的操作

                }else{
                    ToastUtils.showShort("权限没有开启");
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


}
