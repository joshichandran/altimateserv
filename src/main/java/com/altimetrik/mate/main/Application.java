package com.altimetrik.mate.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * TODO: Describe purpose and behavior of Application
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(value="com.altimetrik.mate")
public class Application extends SpringBootServletInitializer {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
