package com.handa.imported;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.handa.springlite.annotation.Bean;
import com.handa.springlite.annotation.Configuration;

@Configuration
public class LocalDateConfiguration {

    @Bean
    LocalDate startLocalDate() {
        return LocalDate.now();
    }

    @Bean
    LocalDateTime startLocalDateTime() {
        return LocalDateTime.now();
    }
}
