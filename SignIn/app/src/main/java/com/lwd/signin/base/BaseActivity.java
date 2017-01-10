package com.lwd.signin.base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.Gson;
import com.lwd.signin.api.Api;
import com.lwd.signin.api.ApiImple;
import com.lwd.signin.api.SimpleMyCallBack;
import com.lwd.signin.common.ActivityPageManager;
import com.lwd.signin.model.HttpExceptionBean;
import com.lwd.signin.utils.ToastUtils;
import com.lwd.signin.widget.DialogLoading;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * User: ZRJ
 * Date: 2016/12/1
 * Email: 471564517@qq.com
 * Description: Acticity基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    protected AppCompatActivity mContext;
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected CompositeSubscription mCompositeSubscription;
    /**
     * 加载对话框
     */
    protected DialogLoading loading;
    /**
     * 来自哪个 页面
     */
    protected String fromWhere;
    /**
     * 页面布局的 根view
     */
    protected View mContentView;
    /**
     * Api类的包装 对象
     */
    protected ApiImple mApiImple;


    public T presenter;

    public static int option;
    public View decorView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置不能横屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        mContext = this;
        //Activity管理
        ActivityPageManager.getInstance().addActivity(this);

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= 21) {
            decorView = getWindow().getDecorView();
            option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(option);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

    }



    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(view);
    }
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                            | View.SYSTEM_UI_FLAG_FULLSCREEN
//                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
//        }
//    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mContentView = view;
        //初始化页面
        init();
    }

    /**
     * 初始化页面
     */
    public void init() {
        initFromWhere();
//        initView();
//        bindEvent();

    }

    /**
     * 初始化 Api  更具需要初始化
     */
    public void initApi() {
        //创建 CompositeSubscription 对象 使用CompositeSubscription来持有所有的Subscriptions，然后在onDestroy()或者onDestroyView()里取消所有的订阅。
        mCompositeSubscription = new CompositeSubscription();
        // 构建 ApiImple 对象
        mApiImple = new ApiImple();
    }

    public ApiImple getApiImple() {
        if (mApiImple == null) {
            mApiImple = new ApiImple();
        }
        return mApiImple;
    }

    public CompositeSubscription getCompositeSubscription() {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        return mCompositeSubscription;
    }

//    /**
//     * 初始化view
//     */
//    public abstract void initView();
//
//
//    /**
//     * 绑定事件
//     */
//    public abstract void bindEvent();


    /**
     * 创建相应的 presenter
     */
    public void createPresenter(T presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }

    }

    /**
     * 取从哪个activity跳过来的，测试用
     */
    protected void initFromWhere() {
        if (null != getIntent().getExtras()) {
            if (getIntent().getExtras().containsKey("fromWhere")) {
                fromWhere = getIntent().getExtras().getString("fromWhere").toString();
            }
        }
    }

    public String getFromWhere() {
        return fromWhere;
    }

    /**
     * 创建观察者  这里对观察着 过滤一次，过滤出我们想要的信息，错误的信息toast
     *
     * @param onNext
     * @param <T>
     * @return
     */
    protected <T> Subscriber newMySubscriber(final SimpleMyCallBack onNext) {
        return new Subscriber<T>() {
            @Override
            public void onCompleted() {
                hideLoadingDialog();
                onNext.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof Api.APIException) {
                    Api.APIException exception = (Api.APIException) e;
                    ToastUtils.showShort(exception.message);
                } else if (e instanceof HttpException) {
                    if (e instanceof HttpException) {
                        ResponseBody body = ((HttpException) e).response().errorBody();
                        try {
                            String json = body.string();
                            Gson gson = new Gson();
                            HttpExceptionBean mHttpExceptionBean = gson.fromJson(json, HttpExceptionBean.class);
                            if (mHttpExceptionBean != null && mHttpExceptionBean.toString() != null) {
                                ToastUtils.showShort(mHttpExceptionBean.toString());
                                onNext.onError(mHttpExceptionBean);
                            }
                        } catch (IOException IOe) {
                            IOe.printStackTrace();
                        }
                    }
                }
//                e.printStackTrace();
                hideLoadingDialog();
            }

            @Override
            public void onNext(T t) {
                if (!mCompositeSubscription.isUnsubscribed()) {
                    onNext.onNext(t);
                }
            }

        };
    }

    /**
     * 将 Fragment添加到Acitvtiy
     *
     * @param fragment
     * @param frameId
     */
    protected void addFragmentToActivity(@NonNull Fragment fragment, int frameId) {
        checkNotNull(fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }


    /**
     * 显示一个Toast信息
     */
    public void showToast(String content) {
        if (content != null) {
            ToastUtils.showToast(content);
        }
    }

    public void showLoadingDialog() {
        if (loading == null) {
            loading = new DialogLoading(this);
        }
        loading.show();
    }

    public void hideLoadingDialog() {
        if (loading != null) {
            loading.dismiss();
        }

    }

    /**
     * 跳转页面
     *
     * @param clazz
     */
    public void skipAct(Class clazz) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra("fromWhere", getClass().getSimpleName());
        startActivity(intent);
    }

    public void skipAct(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        intent.putExtra("fromWhere", getClass().getSimpleName());
        startActivity(intent);
    }

    public void skipAct(Class clazz, Bundle bundle, int flags) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        intent.putExtra("fromWhere", getClass().getSimpleName());
        intent.setFlags(flags);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Acitvity 释放子view资源
        ActivityPageManager.unbindReferences(mContentView);
        ActivityPageManager.getInstance().removeActivity(this);
        mContentView = null;
        //一旦调用了 CompositeSubscription.unsubscribe()，这个CompositeSubscription对象就不可用了,
        // 如果还想使用CompositeSubscription，就必须在创建一个新的对象了。
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
        //解绑 presenter
        if (presenter != null) {
            presenter.unsubscribe();
        }
    }


}
