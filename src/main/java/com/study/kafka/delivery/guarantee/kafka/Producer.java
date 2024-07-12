package com.study.kafka.delivery.guarantee.kafka;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Getter
public class Producer {
    private static final String TOPIC = "test_topic";

    private AtomicInteger messageCount = new AtomicInteger(0);

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        try {
            kafkaTemplate.send(TOPIC, message);
        } finally {
            messageCount.incrementAndGet();
        }

//        // Exactly once
//        kafkaTemplate.executeInTransaction(kafkaOperations -> {
//            kafkaOperations.send(TOPIC, message);
//            messageCount.incrementAndGet();
//            return true;
//        });
    }
}