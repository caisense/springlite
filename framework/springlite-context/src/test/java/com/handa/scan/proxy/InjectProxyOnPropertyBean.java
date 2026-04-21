package com.handa.scan.proxy;

import com.handa.springlite.annotation.Autowired;
import com.handa.springlite.annotation.Component;

@Component
public class InjectProxyOnPropertyBean {

    @Autowired
    public OriginBean injected;
}
