package com.handa.springlite.jdbc;

import javax.sql.DataSource;

import com.handa.springlite.annotation.Autowired;
import com.handa.springlite.annotation.Bean;
import com.handa.springlite.annotation.Configuration;
import com.handa.springlite.annotation.Value;
import com.handa.springlite.jdbc.tx.DataSourceTransactionManager;
import com.handa.springlite.jdbc.tx.PlatformTransactionManager;
import com.handa.springlite.jdbc.tx.TransactionalBeanPostProcessor;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class JdbcConfiguration {

    @Bean(destroyMethod = "close")
    DataSource dataSource(
            // properties:
            @Value("${springlite.datasource.url}") String url, //
            @Value("${springlite.datasource.username}") String username, //
            @Value("${springlite.datasource.password}") String password, //
            @Value("${springlite.datasource.driver-class-name:}") String driver, //
            @Value("${springlite.datasource.maximum-pool-size:20}") int maximumPoolSize, //
            @Value("${springlite.datasource.minimum-pool-size:1}") int minimumPoolSize, //
            @Value("${springlite.datasource.connection-timeout:30000}") int connTimeout //
    ) {
        var config = new HikariConfig();
        config.setAutoCommit(false);
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        if (driver != null) {
            config.setDriverClassName(driver);
        }
        config.setMaximumPoolSize(maximumPoolSize);
        config.setMinimumIdle(minimumPoolSize);
        config.setConnectionTimeout(connTimeout);
        return new HikariDataSource(config);
    }

    @Bean
    JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    TransactionalBeanPostProcessor transactionalBeanPostProcessor() {
        return new TransactionalBeanPostProcessor();
    }

    @Bean
    PlatformTransactionManager platformTransactionManager(@Autowired DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}
