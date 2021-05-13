package com.ystan.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Application.
 *
 * @author ystan
 */
@Configuration
@Import({DatabaseModuleConfig.class, PrincipalModuleConfig.class})
@SpringBootApplication
public class ScheduleManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleManagerApplication.class, args);
    }
}
