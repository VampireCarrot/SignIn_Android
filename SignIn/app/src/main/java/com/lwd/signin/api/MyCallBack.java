package com.lwd.signin.api;


import com.lwd.signin.model.HttpExceptionBean;

/**
 * User: ZRJ
 * Date: 2016/11/30
 * Email: 471564517@qq.com
 * Description: 发送请求回调接口
 */

interface MyCallBack<T>  {
   void onCompleted();
   void onError(HttpExceptionBean mHttpExceptionBean);
   void onNext(T t);
}
