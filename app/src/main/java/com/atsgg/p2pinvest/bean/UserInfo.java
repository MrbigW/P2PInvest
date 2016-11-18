package com.atsgg.p2pinvest.bean;

/**
 * Created by MrbigW on 2016/11/16.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: -.-
 * -------------------=.=------------------------
 */

public class UserInfo {


    /**
     * data : {"UF_ACC":"shkstart","UF_AVATAR_URL":"http://192.168.1.146:8080/P2PInvest/images/tx.jpg","UF_IS_CERT":"1","UF_PHONE":"13012341234"}
     * success : true
     */

    private DataBean data;
    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean {
        /**
         * UF_ACC : shkstart
         * UF_AVATAR_URL : http://192.168.1.146:8080/P2PInvest/images/tx.jpg
         * UF_IS_CERT : 1
         * UF_PHONE : 13012341234
         */

        private String UF_ACC;
        private String UF_AVATAR_URL;
        private String UF_IS_CERT;
        private String UF_PHONE;

        public String getUF_ACC() {
            return UF_ACC;
        }

        public void setUF_ACC(String UF_ACC) {
            this.UF_ACC = UF_ACC;
        }

        public String getUF_AVATAR_URL() {
            return UF_AVATAR_URL;
        }

        public void setUF_AVATAR_URL(String UF_AVATAR_URL) {
            this.UF_AVATAR_URL = UF_AVATAR_URL;
        }

        public String getUF_IS_CERT() {
            return UF_IS_CERT;
        }

        public void setUF_IS_CERT(String UF_IS_CERT) {
            this.UF_IS_CERT = UF_IS_CERT;
        }

        public String getUF_PHONE() {
            return UF_PHONE;
        }

        public void setUF_PHONE(String UF_PHONE) {
            this.UF_PHONE = UF_PHONE;
        }
    }
}
