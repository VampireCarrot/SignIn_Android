package com.lwd.signin.api;


import com.lwd.signin.model.HttpExceptionBean;

/**
 * User: ZRJ
 * Date: 2016/11/30
 * Email: 471564517@qq.com
 * Description: MyCallBack 的一个简单实现，onNext（）方法一定要重写，onCompleted()和onError 更需要重写
 */

public abstract class SimpleMyCallBack<T> implements MyCallBack<T>{
    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(HttpExceptionBean mHttpExceptionBean) {
    }
}
