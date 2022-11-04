package com.securityDevelopment;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class SecurityDevelopmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityDevelopmentApplication.class, args);
    }

}
