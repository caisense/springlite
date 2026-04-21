package com.handa.scan.proxy;

import com.handa.springlite.annotation.Autowired;
import com.handa.springlite.annotation.Component;

@Component
public class InjectProxyOnConstructorBean {

    public final OriginBean injected;

    public InjectProxyOnConstructorBean(@Autowired OriginBean injected) {
        this.injected = injected;
    }
}
