package com.atsgg.p2pinvest.common;

/**
 * Created by MrbigW on 2016/11/12.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: 配置网络请求相关的地址
 * -------------------=.=------------------------
 */

public class AppNetConfig {

    public static final String HOST = "192.168.1.146";

    public static final String BASE_URL = "http://" + HOST + ":8080/P2PInvest/";

    public static final String INDEX = BASE_URL + "index";// 访问首页

    public static final String LOGIN = BASE_URL + "login";// 登陆

    public static final String PRODUCT = BASE_URL + "product"; // 产品

    public static final String UPDATE = BASE_URL + "update.json";// 访问服务器段版本

}
