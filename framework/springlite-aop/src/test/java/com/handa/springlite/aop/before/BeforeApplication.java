package com.handa.springlite.aop.before;

import com.handa.springlite.annotation.Bean;
import com.handa.springlite.annotation.ComponentScan;
import com.handa.springlite.annotation.Configuration;
import com.handa.springlite.aop.AroundProxyBeanPostProcessor;

@Configuration
@ComponentScan
public class BeforeApplication {

    @Bean
    AroundProxyBeanPostProcessor createAroundProxyBeanPostProcessor() {
        return new AroundProxyBeanPostProcessor();
    }
}
