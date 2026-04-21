package com.handa.springlite.aop.after;

import com.handa.springlite.annotation.Bean;
import com.handa.springlite.annotation.ComponentScan;
import com.handa.springlite.annotation.Configuration;
import com.handa.springlite.aop.AroundProxyBeanPostProcessor;

@Configuration
@ComponentScan
public class AfterApplication {

    @Bean
    AroundProxyBeanPostProcessor createAroundProxyBeanPostProcessor() {
        return new AroundProxyBeanPostProcessor();
    }
}
