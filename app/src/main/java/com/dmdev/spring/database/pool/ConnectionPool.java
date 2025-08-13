package com.dmdev.spring.database.pool;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component("pool1")
@ConfigurationProperties(prefix = "db")
@Getter
@Setter
@Validated
public class ConnectionPool {

    public ConnectionPool() {
        // no-arg constructor for Spring
    }

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Min(1)
    private Integer poolSize;

    @NotBlank
    private String driver;

    @NotBlank
    private String url;

    @NotEmpty
    private List<String> hosts;
    
    // Конструктор с параметрами
    public ConnectionPool(String name, int size) {
        this.username = name;
        this.poolSize = size;
        // Инициализация пула соединений
    }

    @PostConstruct
    private void init() {
        log.info("Init connection pool: " + this);
    }

    @PreDestroy
    private void destroy() {
        log.info("Clean connection pool");
    }

    @Override
    public String toString() {
        return "ConnectionPool{" +
                "username='" + username + '\'' +
                ", password='***'" +   // don't print password
                ", poolSize=" + poolSize +
                ", driver='" + driver + '\'' +
                ", url='" + url + '\'' +
                ", hosts=" + hosts +
                '}';
    }
}
