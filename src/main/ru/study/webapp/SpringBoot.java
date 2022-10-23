package ru.study.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "ru.study.webapp")
@EntityScan("ru.study.webapp")
public class SpringBoot {
    public static void main(String[] args) {
        SpringApplication.run(SpringBoot.class, args);
    }
}