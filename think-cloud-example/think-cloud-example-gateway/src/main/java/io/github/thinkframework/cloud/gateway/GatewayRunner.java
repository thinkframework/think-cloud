package io.github.thinkframework.cloud.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;


public class GatewayRunner implements ApplicationRunner, CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(GatewayRunner.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.debug("ApplicationRunner run: {}.",args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.debug("CommandLineRunner run: {}.",args);
    }
}
