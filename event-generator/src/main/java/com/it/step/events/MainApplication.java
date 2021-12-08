package com.it.step.events;

import com.it.step.events.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication
        implements CommandLineRunner {

    @Autowired
    private Producer producer;

    private static final Logger LOG = LoggerFactory
            .getLogger(MainApplication.class);


    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Override
    public void run(String... args) {
        int i = 0;
        int counter = Integer.parseInt(System.getProperty("num.generated.events", "100"));

        LOG.info("Start sending {} events", counter);
        while(i < counter) {
            producer.send();
            i++;
        }

    }

}