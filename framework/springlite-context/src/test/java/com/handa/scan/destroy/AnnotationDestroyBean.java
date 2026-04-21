package com.handa.scan.destroy;

import com.handa.springlite.annotation.Component;
import com.handa.springlite.annotation.Value;

import jakarta.annotation.PreDestroy;

@Component
public class AnnotationDestroyBean {

    @Value("${app.title}")
    public String appTitle;

    @PreDestroy
    void destroy() {
        this.appTitle = null;
    }
}
