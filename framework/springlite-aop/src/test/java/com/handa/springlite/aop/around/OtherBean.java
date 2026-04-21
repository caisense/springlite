package com.handa.springlite.aop.around;

import com.handa.springlite.annotation.Autowired;
import com.handa.springlite.annotation.Component;
import com.handa.springlite.annotation.Order;

@Order(0)
@Component
public class OtherBean {

    public OriginBean origin;

    public OtherBean(@Autowired OriginBean origin) {
        this.origin = origin;
    }
}
