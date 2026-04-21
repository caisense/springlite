package com.handa.scan.init;

import com.handa.springlite.annotation.Bean;
import com.handa.springlite.annotation.Configuration;
import com.handa.springlite.annotation.Value;

@Configuration
public class SpecifyInitConfiguration {

    @Bean(initMethod = "init")
    SpecifyInitBean createSpecifyInitBean(@Value("${app.title}") String appTitle, @Value("${app.version}") String appVersion) {
        return new SpecifyInitBean(appTitle, appVersion);
    }
}
