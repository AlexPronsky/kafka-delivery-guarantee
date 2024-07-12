package com.study.kafka.delivery.guarantee;

import com.study.kafka.delivery.guarantee.kafka.Consumer;
import com.study.kafka.delivery.guarantee.kafka.Producer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class KafkaDeliveryGuaranteeApplication {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext context = SpringApplication.run(KafkaDeliveryGuaranteeApplication.class, args);

		// Time for initialisation
		Thread.sleep(10000);

		// Produce messages
		int messageCount = 10000000;
//		int messageCount = 20000;
		Producer producer = context.getBean(Producer.class);
		Consumer consumer = context.getBean(Consumer.class);
		consumer.setExpectedMessageCount(messageCount);

		for (int i = 0; i < messageCount; i++) {
			if (i % 1000 == 0) {
				log.info("Messages produced: " + producer.getMessageCount().toString());
			}
			producer.sendMessage("Hello, Kafka!");
		}
		log.info("Messages produced: " + producer.getMessageCount().toString());
	}
}
