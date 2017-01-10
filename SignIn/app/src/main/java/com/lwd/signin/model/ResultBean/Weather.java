package com.lwd.signin.model.ResultBean;

/**
 * User: ZRJ
 * Date: 2016/12/1
 * Email: 471564517@qq.com
 * Description:
 */
public class Weather {

    /**
     * code : 1
     * msg : Sucess
     * counts : 2362
     * data : {"city_id":"CH010100","city_name":"北京","last_update":"2016-03-10 08:00:00","aqi":"34","pollutant":"pm10","pm25":"9","pm10":"34","so2":"3","no2":"34","level":"一级","grade":"优","color":"绿色","health":"空气质量令人满意，基本无空气污染","measure":"各类人群可正常活动"}
     */

    private int code;
    private String msg;
    private int counts;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * city_id : CH010100
         * city_name : 北京
         * last_update : 2016-03-10 08:00:00
         * aqi : 34
         * pollutant : pm10
         * pm25 : 9
         * pm10 : 34
         * so2 : 3
         * no2 : 34
         * level : 一级
         * grade : 优
         * color : 绿色
         * health : 空气质量令人满意，基本无空气污染
         * measure : 各类人群可正常活动
         */

        private String city_id;
        private String city_name;
        private String last_update;
        private String aqi;
        private String pollutant;
        private String pm25;
        private String pm10;
        private String so2;
        private String no2;
        private String level;
        private String grade;
        private String color;
        private String health;
        private String measure;

        public String getCity_id() {
            return city_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getLast_update() {
            return last_update;
        }

        public void setLast_update(String last_update) {
            this.last_update = last_update;
        }

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getPollutant() {
            return pollutant;
        }

        public void setPollutant(String pollutant) {
            this.pollutant = pollutant;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }

        public String getPm10() {
            return pm10;
        }

        public void setPm10(String pm10) {
            this.pm10 = pm10;
        }

        public String getSo2() {
            return so2;
        }

        public void setSo2(String so2) {
            this.so2 = so2;
        }

        public String getNo2() {
            return no2;
        }

        public void setNo2(String no2) {
            this.no2 = no2;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getHealth() {
            return health;
        }

        public void setHealth(String health) {
            this.health = health;
        }

        public String getMeasure() {
            return measure;
        }

        public void setMeasure(String measure) {
            this.measure = measure;
        }

        @Override
        public String toString() {
            return "{" +
                    "city_id='" + city_id + '\'' +
                    ", city_name='" + city_name + '\'' +
                    ", last_update='" + last_update + '\'' +
                    ", aqi='" + aqi + '\'' +
                    ", pollutant='" + pollutant + '\'' +
                    ", pm25='" + pm25 + '\'' +
                    ", pm10='" + pm10 + '\'' +
                    ", so2='" + so2 + '\'' +
                    ", no2='" + no2 + '\'' +
                    ", level='" + level + '\'' +
                    ", grade='" + grade + '\'' +
                    ", color='" + color + '\'' +
                    ", health='" + health + '\'' +
                    ", measure='" + measure + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", counts=" + counts +
                ", data=" + data.toString() +
                '}';
    }
}
