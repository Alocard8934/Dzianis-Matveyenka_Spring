package com.dmdev.spring;

import com.dmdev.spring.config.ApplicationConfiguration;
import com.dmdev.spring.database.pool.ConnectionPool;
import com.dmdev.spring.database.repository.CompanyRepository;
import com.dmdev.spring.database.repository.CrudRepository;
import com.dmdev.spring.database.repository.UserRepository;
import com.dmdev.spring.ioc.Container;
import com.dmdev.spring.service.UserService;
import java.io.Serializable;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppliactionRunner {

    public static void main(String[] args) {
        String value = "hello";
        System.out.println(CharSequence.class.isAssignableFrom(value.getClass()));
        System.out.println(BeanFactoryPostProcessor.class.isAssignableFrom(value.getClass()));
        System.out.println(Serializable.class.isAssignableFrom(value.getClass()));

        try (var context = new AnnotationConfigApplicationContext()) {
            context.register(ApplicationConfiguration.class);
            context.getEnvironment().setActiveProfiles("web", "prod");
            context.refresh();

            var connectionPool = context.getBean("pool1", ConnectionPool.class);

            System.out.println(connectionPool);
            /*var companyRepository = context.getBean("companyRepository", CompanyRepository.class);
            System.out.println(companyRepository);*/
            var companyRepository = context.getBean("companyRepository", CrudRepository.class);
            System.out.println(companyRepository.findById(1));

        }
    }
}
