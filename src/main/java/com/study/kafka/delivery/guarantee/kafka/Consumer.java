package com.study.kafka.delivery.guarantee.kafka;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
@Getter
@Setter
public class Consumer {
    private int expectedMessageCount;
    private AtomicInteger messageCount = new AtomicInteger(0);

    @KafkaListener(topics = "test_topic", groupId = "group_id")
    public void consume(String message) {
        int count = messageCount.incrementAndGet();

        if (count < expectedMessageCount - 100) {
            if (count % 10000 == 0) {
                log.info("Messages received: " + count + ", message: " + message);
            }
        } else {
            log.info("Messages received: " + count + ", message: " + message);
        }
    }
}