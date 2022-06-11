package com.peter.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HelloPeterApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloPeterApplication.class, args);
    }

}
