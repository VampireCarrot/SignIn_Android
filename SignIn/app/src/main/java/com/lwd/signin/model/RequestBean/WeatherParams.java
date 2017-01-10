package com.lwd.signin.model.RequestBean;

/**
 * User: ZRJ
 * Date: 2016/12/1
 * Email: 471564517@qq.com
 * Description:
 */
public class WeatherParams {

    private String cityname;
    private String dtype;
    private int format;
    private String key;

    public WeatherParams(String cityname, String dtype,int format,String key) {
        this.cityname = cityname;
        this.dtype = dtype;
        this.format = format;
        this.key = key;
    }


}
