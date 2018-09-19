/**
 *
 * <p>Filename: Application.java
 * <p>Created At: Aug 19th 2018
 *
 * @author DatNT
 */

package com.coffeeshop.app;

import org.apache.ibatis.type.MappedTypes;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.coffeeshop.model.Users;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@MappedTypes(Users.class)
@MapperScan("com.coffeeshop.mybatis.mapper")
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    private static Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Starting Spring Boot application..");
        SpringApplication.run(Application.class, args);
    }
}
