
package com.dmdev.spring.bpp;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.function.Consumer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class InjectBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware{
    
    private ApplicationContext applicationContext;
    
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException{
        Arrays.stream(bean.getClass().getDeclaredFields()).filter(field -> field.isAnnotationPresent(InjectBean.class)).forEach(new Consumer<Field>() {
            @Override
            public void accept(Field field) {
                Object beanToInject = applicationContext.getBean(field.getType());
                field.setAccessible(true);
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, bean, beanToInject);
            }
        });
        
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
