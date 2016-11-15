package com.atsgg.p2pinvest.bean;

import java.util.List;

/**
 * Created by MrbigW on 2016/11/15.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class Product {


    /**
     * data : [{"id":"1","name":"超级新手计划","money":"10","yearRate":"8.00","suodingDays":"30","minTouMoney":"100","memberNum":"100","progress":"50"},{"id":"2","name":"乐享活系列90天计划","money":"20","yearRate":"8.00","suodingDays":"40","minTouMoney":"1000","memberNum":"100","progress":"60"},{"id":"3","name":"钱包计划","money":"200","yearRate":"11.00","suodingDays":"100","minTouMoney":"10000","memberNum":"400","progress":"90"},{"id":"4","name":"90天理财计划(加息5%)","money":"40","yearRate":"15.00","suodingDays":"80","minTouMoney":"10000","memberNum":"200","progress":"30"},{"id":"5","name":"林业局投资商业经营","money":"500","yearRate":"10.00","suodingDays":"150","minTouMoney":"50000","memberNum":"150","progress":"40"},{"id":"6","name":"中学老师购买车辆","money":"10","yearRate":"8.00","suodingDays":"60","minTouMoney":"5000","memberNum":"60","progress":"40"},{"id":"7","name":"屌丝下海经商计划","money":"20","yearRate":"13.00","suodingDays":"120","minTouMoney":"10000","memberNum":"80","progress":"90"},{"id":"8","name":"新西游影视拍摄投资","money":"500","yearRate":"14.00","suodingDays":"100","minTouMoney":"2000","memberNum":"1000","progress":"80"},{"id":"9","name":"Java培训老师自己周转","money":"5","yearRate":"8.00","suodingDays":"90","minTouMoney":"1000","memberNum":"100","progress":"40"},{"id":"10","name":"阿里巴巴洗钱计划","money":"1000","yearRate":"10.00","suodingDays":"200","minTouMoney":"100000","memberNum":"1000","progress":"60"}]
     * success : true
     */

    private boolean success;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 超级新手计划
         * money : 10
         * yearRate : 8.00
         * suodingDays : 30
         * minTouMoney : 100
         * memberNum : 100
         * progress : 50
         */

        private String id;
        private String name;
        private String money;
        private String yearRate;
        private String suodingDays;
        private String minTouMoney;
        private String memberNum;
        private String progress;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getYearRate() {
            return yearRate;
        }

        public void setYearRate(String yearRate) {
            this.yearRate = yearRate;
        }

        public String getSuodingDays() {
            return suodingDays;
        }

        public void setSuodingDays(String suodingDays) {
            this.suodingDays = suodingDays;
        }

        public String getMinTouMoney() {
            return minTouMoney;
        }

        public void setMinTouMoney(String minTouMoney) {
            this.minTouMoney = minTouMoney;
        }

        public String getMemberNum() {
            return memberNum;
        }

        public void setMemberNum(String memberNum) {
            this.memberNum = memberNum;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }


        @Override
        public String toString() {
            return "DataBean{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", money='" + money + '\'' +
                    ", yearRate='" + yearRate + '\'' +
                    ", suodingDays='" + suodingDays + '\'' +
                    ", minTouMoney='" + minTouMoney + '\'' +
                    ", memberNum='" + memberNum + '\'' +
                    ", progress='" + progress + '\'' +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "Product{" +
                "success=" + success +
                ", data=" + data +
                '}';
    }
}
