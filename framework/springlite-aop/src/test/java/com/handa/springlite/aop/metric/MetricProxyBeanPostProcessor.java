package com.handa.springlite.aop.metric;

import com.handa.springlite.annotation.Component;
import com.handa.springlite.aop.AnnotationProxyBeanPostProcessor;

@Component
public class MetricProxyBeanPostProcessor extends AnnotationProxyBeanPostProcessor<Metric> {

}
