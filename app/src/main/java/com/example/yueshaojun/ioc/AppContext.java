package com.example.yueshaojun.ioc;

import android.app.Application;

import com.example.yueshaojun.ioc.util.Parser;


/**
 *
 * @author yueshaojun
 * @date 2018/7/25
 */

public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parser.parse(this);
    }
}
