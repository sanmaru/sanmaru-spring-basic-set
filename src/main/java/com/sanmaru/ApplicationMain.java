package com.sanmaru;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;

@SpringBootApplication
public class ApplicationMain {

    private static Logger logger = LoggerFactory.getLogger(ApplicationMain.class);

    public static void main(String[] args) {
        logger.info("==== logging Info Test =====");
        logger.debug("==== logging Debug Test =====");
        System.out.println("========System.out Test ");
        SpringApplication.run(ApplicationMain.class, args);
    }

}
