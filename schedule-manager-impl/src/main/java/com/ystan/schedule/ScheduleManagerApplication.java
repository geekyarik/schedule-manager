package com.ystan.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Have to use specified package in each module
 */
@SpringBootApplication(scanBasePackages = "com.ystan.schedule")
public class ScheduleManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScheduleManagerApplication.class, args);
	}

}
