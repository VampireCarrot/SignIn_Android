package com.lwd.signin.model;

/**
 * User: ZRJ
 * Date: 2016/11/30
 * Email: 471564517@qq.com
 * Description: HTTP请求异常Bean
 */

public class HttpExceptionBean {

    /**
     * code : AccountNotExist
     * message : 账户名不存在
     * body :
     */
    private String resultcode;
    private String reason;
    private String result;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "HttpExceptionBean{" +
                "resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
