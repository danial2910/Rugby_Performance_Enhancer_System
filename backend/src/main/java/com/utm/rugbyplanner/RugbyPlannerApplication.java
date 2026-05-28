package com.utm.rugbyplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * AI-Powered Rugby Diet & Fitness Planner
 * Universiti Teknologi Malaysia — Faculty of Computing
 *
 * @author Muhammad Danial Syafiq Bin Ermiza
 */
@SpringBootApplication
@EnableMongoAuditing   // enables @CreatedDate / @LastModifiedDate on MongoDB documents
@EnableAsync           // enables @Async for non-blocking email sending (UC010)
public class RugbyPlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RugbyPlannerApplication.class, args);
    }
}
