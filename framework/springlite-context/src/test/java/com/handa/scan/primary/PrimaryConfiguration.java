package com.handa.scan.primary;

import com.handa.springlite.annotation.Bean;
import com.handa.springlite.annotation.Configuration;
import com.handa.springlite.annotation.Primary;

@Configuration
public class PrimaryConfiguration {

    @Primary
    @Bean
    DogBean husky() {
        return new DogBean("Husky");
    }

    @Bean
    DogBean teddy() {
        return new DogBean("Teddy");
    }
}
