package com.app.medical.patient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(final String[] args) {

        LOGGER.info("patient service starting ...");

        SpringApplication.run(Main.class, args);

        LOGGER.info("patient service started !");
    }
}
