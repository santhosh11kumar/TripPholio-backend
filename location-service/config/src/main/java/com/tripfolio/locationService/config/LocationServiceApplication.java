package com.tripfolio.locationService.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.tripfolio.locationService")
@EntityScan(basePackages = "com.tripfolio.locationService.adapters.repository.entity")
@EnableJpaRepositories(basePackages = "com.tripfolio.locationService.adapters.repository")
public class LocationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LocationServiceApplication.class, args);
    }
}
