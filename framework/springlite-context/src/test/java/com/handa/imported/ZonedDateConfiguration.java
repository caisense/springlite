package com.handa.imported;

import java.time.ZonedDateTime;

import com.handa.springlite.annotation.Bean;
import com.handa.springlite.annotation.Configuration;

@Configuration
public class ZonedDateConfiguration {

    @Bean
    ZonedDateTime startZonedDateTime() {
        return ZonedDateTime.now();
    }
}
