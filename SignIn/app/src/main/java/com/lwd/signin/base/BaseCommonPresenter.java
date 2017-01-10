package com.lwd.signin.base;

import com.google.gson.Gson;
import com.lwd.signin.api.Api;
import com.lwd.signin.api.ApiImple;
import com.lwd.signin.api.SimpleMyCallBack;
import com.lwd.signin.model.HttpExceptionBean;
import com.lwd.signin.utils.ToastUtils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.HttpException;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * User: ZRJ
 * Date: 2016/12/1
 * Email: 471564517@qq.com
 * Description:
 */

public  class BaseCommonPresenter<T extends BaseView> {
    /**
     * Api类的包装 对象
     */
    protected ApiImple apiImple;
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected CompositeSubscription mCompositeSubscription;

    public T view;

    public BaseCommonPresenter(T view) {
        //创建 CompositeSubscription 对象 使用CompositeSubscription来持有所有的Subscriptions，然后在onDestroy()或者onDestroyView()里取消所有的订阅。
        mCompositeSubscription = new CompositeSubscription();
        // 构建 ApiWrapper 对象
        apiImple = new ApiImple();
        this.view  = view;
    }

    /**
     * 创建观察者  这里对观察着 过滤一次，过滤出我们想要的信息，错误的信息toast
     *
     * @param onNext
     * @param <E>
     * @return
     */
    protected <E> Subscriber newMySubscriber(final SimpleMyCallBack onNext) {
        return new Subscriber<E>() {
            @Override
            public void onCompleted() {
                if (view != null) {
                    view.hideLoading();
                }
                onNext.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                if (e instanceof Api.APIException) {
                    Api.APIException exception = (Api.APIException) e;
                    if (view != null) {
                        ToastUtils.showShort(exception.message);
                    }
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
                if (view != null) {
                    view.hideLoading();
                }
            }

            @Override
            public void onNext(E t) {
                if (!mCompositeSubscription.isUnsubscribed()) {
                    onNext.onNext(t);
                }
            }

        };
    }


    /**
     * 解绑 CompositeSubscription
     */
    public void unsubscribe() {
        if(mCompositeSubscription != null){
            mCompositeSubscription.unsubscribe();
        }
    }

}
