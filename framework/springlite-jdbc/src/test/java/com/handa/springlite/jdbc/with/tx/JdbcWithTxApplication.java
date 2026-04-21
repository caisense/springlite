package com.handa.springlite.jdbc.with.tx;

import com.handa.springlite.annotation.ComponentScan;
import com.handa.springlite.annotation.Configuration;
import com.handa.springlite.annotation.Import;
import com.handa.springlite.jdbc.JdbcConfiguration;

@ComponentScan
@Configuration
@Import(JdbcConfiguration.class)
public class JdbcWithTxApplication {

}
