package com.handa.springlite.aop.after;

import java.lang.reflect.Method;

import com.handa.springlite.annotation.Component;
import com.handa.springlite.aop.AfterInvocationHandlerAdapter;

@Component
public class PoliteInvocationHandler extends AfterInvocationHandlerAdapter {

    @Override
    public Object after(Object proxy, Object returnValue, Method method, Object[] args) {
        if (returnValue instanceof String s) {
            if (s.endsWith(".")) {
                return s.substring(0, s.length() - 1) + "!";
            }
        }
        return returnValue;
    }
}
