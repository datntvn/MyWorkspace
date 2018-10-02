/**
 * @author DatNT29
 *
 * File       : AdapterApplication.java
 * Created On : 10/09/2018 (dd/MM/YYYY)
 */

package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AdapterApplication {

    private static Logger logger = LogManager.getLogger(AdapterApplication.class);
    
    public static void main(String[] args) {
        logger.info("Starting Spring Boot application..");
        SpringApplication.run(AdapterApplication.class, args);
    }

    @Bean
    public RestTemplate getRestClient(RestTemplateBuilder builder) {
        // TODO: adding more configuration here later if system require.
        return builder.build();
    }
}
