package com.handa.scan.destroy;

import com.handa.springlite.annotation.Bean;
import com.handa.springlite.annotation.Configuration;
import com.handa.springlite.annotation.Value;

@Configuration
public class SpecifyDestroyConfiguration {

    @Bean(destroyMethod = "destroy")
    SpecifyDestroyBean createSpecifyDestroyBean(@Value("${app.title}") String appTitle) {
        return new SpecifyDestroyBean(appTitle);
    }
}
