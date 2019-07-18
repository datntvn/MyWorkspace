/**
 *
 * <p>Filename: Application.java
 * <p>Created At: Aug 19th 2018
 *
 * @author DatNT
 */

package com.coffeeshop.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EnableAutoConfiguration
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    private static Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Starting Spring Boot application..");
        SpringApplication.run(Application.class, args);
    }
}
