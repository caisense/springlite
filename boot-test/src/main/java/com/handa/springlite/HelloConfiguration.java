package com.handa.springlite;

import com.handa.springlite.annotation.ComponentScan;
import com.handa.springlite.annotation.Configuration;
import com.handa.springlite.annotation.Import;
import com.handa.springlite.jdbc.JdbcConfiguration;
import com.handa.springlite.web.WebMvcConfiguration;

@ComponentScan
@Configuration
@Import({ JdbcConfiguration.class, WebMvcConfiguration.class })
public class HelloConfiguration {

}
