package com.bootkafka.springbootkafkademo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public final class ProducerService {

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);
    private final String TOPIC = "kafkaTopic";
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        logger.info(String.format("Producing Message : %s", message));

        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(TOPIC, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.info("Unable to send message : ", message, throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                logger.info("Send Message : ", message, stringStringSendResult.getRecordMetadata().offset());
            }
        });
    }
}
