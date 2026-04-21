package com.handa.springlite.jdbc.tx;

import com.handa.springlite.annotation.Transactional;
import com.handa.springlite.aop.AnnotationProxyBeanPostProcessor;

public class TransactionalBeanPostProcessor extends AnnotationProxyBeanPostProcessor<Transactional> {

}
