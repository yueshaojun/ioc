package com.example.yueshaojun.ioc.bean;

/**
 * Created by yueshaojun on 2018/7/25.
 */

public class Computer {
    private Host host;
    public Computer(Host host){
        this.host = host;
    }
    public Host getHost(){
        return this.host;
    }
}
