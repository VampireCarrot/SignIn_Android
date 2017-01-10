package com.lwd.signin.api;


import com.lwd.signin.model.RequestBean.FastParams;
import com.lwd.signin.model.ResultBean.FastDi;
import com.lwd.signin.model.ResultBean.Login;

import okhttp3.RequestBody;
import rx.Observable;

/**
 * User: ZRJ
 * Date: 2016/12/1
 * Email: 471564517@qq.com
 * Description: 实现包装
 */
public class ApiImple extends Api {


    public Observable<FastDi> getFastdiInfo(FastParams fastParams) {
        return applySchedulers(getService().getFastdiInfo(fastParams));
    }

    public Observable<Login> getdoLogin(String username, String password) {
        return applySchedulers(getService().doLogin(username, password));

    }

    public Observable<Object> getdofile(RequestBody file) {
        return applySchedulers((Observable<Object>) getService().uploadImage("123", file));
    }

}
