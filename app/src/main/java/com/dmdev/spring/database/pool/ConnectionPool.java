
package com.dmdev.spring.database.pool;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.InitializingBean;

public class ConnectionPool implements InitializingBean{
    private String username;
    private Integer poolSize;
    private List<Object> args;
    private  Map<String, Object> properties;

    public ConnectionPool() {
    }
    

    public ConnectionPool(String username, Integer poolSize, List args, Map properties) {
        this.username = username;
        this.poolSize = poolSize;
        this.args = args;
        this.properties = properties;
    } 

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
    
    @PostConstruct
    private void init(){
        System.out.println("Init connection pool");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Properties set");
    }
    
    @PreDestroy
    private void destroy(){
        System.out.println("Clean connection pool");
    }
    
}
