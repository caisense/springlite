package com.handa.springlite.aop.around;

import com.handa.springlite.annotation.Around;
import com.handa.springlite.annotation.Component;
import com.handa.springlite.annotation.Value;

@Component
@Around("aroundInvocationHandler")
public class OriginBean {

    @Value("${customer.name}")
    public String name;

    @Polite
    public String hello() {
        return "Hello, " + name + ".";
    }

    public String morning() {
        return "Morning, " + name + ".";
    }
}
