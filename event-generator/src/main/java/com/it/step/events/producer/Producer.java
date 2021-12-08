package com.it.step.events.producer;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private static final Logger LOG = LoggerFactory
            .getLogger(Producer.class);

    private static final String TOPIC_NAME = "events-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send() {
        final String value = Randomizer.getRandomValue();

        if (StringUtils.isNotBlank(value)) {
            kafkaTemplate.send(TOPIC_NAME, value);
//            LOG.info("sent value to kafka : {}", value);
        }
    }
}
