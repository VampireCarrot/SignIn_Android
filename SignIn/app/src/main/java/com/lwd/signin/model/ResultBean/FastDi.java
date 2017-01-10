package com.lwd.signin.model.ResultBean;

import java.util.List;

/**
 * User: ZRJ
 * Date: 2016/12/1
 * Email: 471564517@qq.com
 * Description:
 */
public class FastDi {

    /**
     * reason : 查询成功
     * result : [{"carrier_code":"ems","carrier_phone":"11183","carrier_name":"EMS"},{"carrier_code":"zhongtong","carrier_phone":"021-39777777","carrier_name":"中通快递"},{"carrier_code":"yousu","carrier_phone":"400-1111-119","carrier_name":"优速快递"},{"carrier_code":"yuantong","carrier_phone":"021-69777888","carrier_name":"圆通速递"},{"carrier_code":"zjs","carrier_phone":"400-6789-000","carrier_name":"宅急送"},{"carrier_code":"debang","carrier_phone":"400-830-5555","carrier_name":"德邦物流"},{"carrier_code":"huitong","carrier_phone":"021-62963636","carrier_name":"百世汇通"},{"carrier_code":"shunfeng","carrier_phone":"400-811-1111","carrier_name":"顺丰速运"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * carrier_code : ems
         * carrier_phone : 11183
         * carrier_name : EMS
         */

        private String carrier_code;
        private String carrier_phone;
        private String carrier_name;

        public String getCarrier_code() {
            return carrier_code;
        }

        public void setCarrier_code(String carrier_code) {
            this.carrier_code = carrier_code;
        }

        public String getCarrier_phone() {
            return carrier_phone;
        }

        public void setCarrier_phone(String carrier_phone) {
            this.carrier_phone = carrier_phone;
        }

        public String getCarrier_name() {
            return carrier_name;
        }

        public void setCarrier_name(String carrier_name) {
            this.carrier_name = carrier_name;
        }


    }


}
