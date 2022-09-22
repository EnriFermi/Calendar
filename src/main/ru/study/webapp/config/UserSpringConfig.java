package ru.study.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.study.webapp.model.configuration.domain.CalendarTemplate;
@Configuration
@ComponentScan("ru.study.webapp")
public class UserSpringConfig {
    @Bean
    public CalendarTemplate calendarTemplate(){
        return new CalendarTemplate();
    }
}
