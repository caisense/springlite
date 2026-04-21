package com.handa.springlite.web;

import java.util.Objects;

import com.handa.springlite.annotation.Autowired;
import com.handa.springlite.annotation.Bean;
import com.handa.springlite.annotation.Configuration;
import com.handa.springlite.annotation.Value;

import jakarta.servlet.ServletContext;

/**
 * 为了简化Web应用程序配置，提供一个WebMvcConfiguration配置：
 */
@Configuration
public class WebMvcConfiguration {

    private static ServletContext servletContext = null;

    /**
     * Set by web listener.
     */
    static void setServletContext(ServletContext ctx) {
        servletContext = ctx;
    }

    @Bean(initMethod = "init")
    ViewResolver viewResolver( //
            @Autowired ServletContext servletContext, //
            @Value("${springlite.web.freemarker.template-path:/WEB-INF/templates}") String templatePath, //
            @Value("${springlite.web.freemarker.template-encoding:UTF-8}") String templateEncoding) {
        return new FreeMarkerViewResolver(servletContext, templatePath, templateEncoding);
    }

    @Bean
    ServletContext servletContext() {
        return Objects.requireNonNull(servletContext, "ServletContext is not set.");
    }
}
