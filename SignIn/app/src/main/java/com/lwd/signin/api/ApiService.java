package com.lwd.signin.api;



import com.lwd.signin.model.RequestBean.FastParams;
import com.lwd.signin.model.ResultBean.FastDi;
import com.lwd.signin.model.ResultBean.Login;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;


/**
 * User: ZRJ
 * Date: 2016/11/30
 * Email: 471564517@qq.com
 * Description: 服务
 */
public interface ApiService {


    /**
     * 获取快递信息
     */

    @POST("expressonline/test/getCarriers.php")
    Observable<FastDi> getFastdiInfo(@Body FastParams fastParams);

    /**
     * 登陆
     */
    @POST("Watch/UserLoginServlet")
    @FormUrlEncoded
    Observable<Login> doLogin(@Field("userName") String username, @Field("password") String password);

    /**
     *    * 上传一张图片    
     */
    /* @param description    
            * @param imgs    * @return    
            */
    @Multipart
    @POST("/upload")
    Call<String> uploadImage(@Part("fileName") String description, @Part("file\"; filename=\"image.png\"") RequestBody imgs);



}
