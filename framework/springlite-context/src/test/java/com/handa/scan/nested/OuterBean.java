package com.handa.scan.nested;

import com.handa.springlite.annotation.Component;

@Component
public class OuterBean {

    @Component
    public static class NestedBean {

    }
}
