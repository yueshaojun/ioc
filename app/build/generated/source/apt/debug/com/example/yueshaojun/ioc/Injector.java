package com.example.yueshaojun.ioc;

import com.example.yueshaojun.ioc.bean.Host;

class Injector {
  public static void bindMember(final MainActivity activity) {
    activity.host = new Host();
  }
}
