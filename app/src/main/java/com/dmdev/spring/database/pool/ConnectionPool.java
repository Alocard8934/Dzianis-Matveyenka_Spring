
package com.dmdev.spring.database.pool;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.InitializingBean;

public class ConnectionPool implements InitializingBean{
    private final String username;
    private final Integer poolSize;
    private final List<Object> args;
    private  Map<String, Object> properties;

    public ConnectionPool(String username, Integer poolSize, List args, Map properties) {
        this.username = username;
        this.poolSize = poolSize;
        this.args = args;
        this.properties = properties;
    } 

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
    
    private void init(){
        System.out.println("Init connection pool");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Properties set");
    }
    private void destroy(){
        System.out.println("Clean connection pool");
    }
    
}
