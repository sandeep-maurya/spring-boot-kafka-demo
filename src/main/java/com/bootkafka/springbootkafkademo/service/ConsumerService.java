package com.bootkafka.springbootkafkademo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(topics = "kafkaTopic", groupId = "group_id")
    public void consume(String message){
        logger.info(String.format("Consumed Message is : %s", message));
    }
}
