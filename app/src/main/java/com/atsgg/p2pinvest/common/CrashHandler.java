package com.atsgg.p2pinvest.common;

import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;

import com.atsgg.p2pinvest.utils.ToastUtil;
import com.atsgg.p2pinvest.utils.UIUtils;

/**
 * Created by MrbigW on 2016/11/12.
 * weChat:1024057635
 * GitHub:MrbigW
 * Usage: 提供一个出现未被捕获异常时，显示捕获类(单例)
 * -------------------=.=------------------------
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    public Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;// 系统默认未捕获异常处理器

    // 懒汉式
    private CrashHandler() {
    }

    public static CrashHandler instance = null;

    public static CrashHandler getInstance() {
        if (instance == null) {
            instance = new CrashHandler();
        }
        return instance;
    }


    public void init() {
        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 将当前的类，作为出现未捕获异常的处理类
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当程序执行过程中，一旦出现未被捕获的异常时，即调用如下的回调方法
     * 处理异常的操作，系统单独的提供了一个分线程来完成
     *
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
//        Log.e("TAG","出现异常，异常原因:"+e.getMessage());
        // android系统中,默认情况下，线程是不可以开启Looper进行消息处理的。
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                ToastUtil.showToast(UIUtils.getContext(), "亲，出现异常了，店小二正在努力修复~");
                Looper.loop();
            }
        }.start();

        // 收集用户的异常信息，并发送给后台
        collectionException(e);

        SystemClock.sleep(2000);

        // 移除栈空间中所有的Acitivity
        ActivityManager.getInstance().removeAll();
        // 结束当前进程
        android.os.Process.killProcess(android.os.Process.myPid());
        // 结束当前虚拟机执行
        System.exit(0);
    }

    private void collectionException(Throwable e) {
        final String exception = e.getMessage();

        // 收集用户的设备信息
        final String message = Build.DEVICE + ":" + Build.MODEL + ":" + Build.PRODUCT + ":" + Build.VERSION.SDK_INT;

        // 模拟联网操作
        new Thread() {
            @Override
            public void run() {
                Log.e("TAG", "异常信息：" + exception + "用户信息：" + message);
            }
        }.start();

    }
}




































