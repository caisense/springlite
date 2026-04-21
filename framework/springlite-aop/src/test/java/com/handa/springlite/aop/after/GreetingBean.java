package com.handa.springlite.aop.after;

import com.handa.springlite.annotation.Around;
import com.handa.springlite.annotation.Component;

@Component
@Around("politeInvocationHandler")
public class GreetingBean {

    public String hello(String name) {
        return "Hello, " + name + ".";
    }

    public String morning(String name) {
        return "Morning, " + name + ".";
    }
}
