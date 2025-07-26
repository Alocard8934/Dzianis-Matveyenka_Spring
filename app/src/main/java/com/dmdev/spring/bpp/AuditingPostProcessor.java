package com.dmdev.spring.bpp;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class AuditingPostProcessor implements BeanPostProcessor {

    private final Map<String, Class<?>> auditBeans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Auditing.class)) {
            auditBeans.put(beanName, bean.getClass());
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = auditBeans.get(beanName);
        if (beanClass != null) {
            Class<?>[] interfaces = beanClass.getInterfaces();
            ClassLoader classLoader = interfaces.length > 0 ? interfaces[0].getClassLoader() : beanClass.getClassLoader();

            return Proxy.newProxyInstance(classLoader, interfaces, (proxy, method, args) -> {
                System.out.println("Audit method " + method.getName());
                var startTime = System.nanoTime();
                try {
                    return method.invoke(bean, args);
                } finally {
                    System.out.println("Time execution: " + (System.nanoTime() - startTime));
                }
            });
        }
        return bean;
    }
}
