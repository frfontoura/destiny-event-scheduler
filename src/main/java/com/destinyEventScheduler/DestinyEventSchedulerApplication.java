package com.destinyEventScheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.destinyEventScheduler")
public class DestinyEventSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DestinyEventSchedulerApplication.class, args);
	}
}
