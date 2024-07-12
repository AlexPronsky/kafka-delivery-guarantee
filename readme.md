# Kafka Delivery Guarantees

This repository contains three branches demonstrating different Kafka delivery guarantees:

1. `main` - "At Least Once" guarantee (default for Kafka)
2. `at-most-once` - "At Most Once" guarantee
3. `exactly-once` - "Exactly Once" guarantee

## Instructions for Testing
1. Run `docker-compose up -d`.
2. Start the application.
3. Restart the network several times during the processing time.

### How to Restart the Network
1. docker network disconnect kafka-delivery-guarantee_kafka_network kafka-delivery-guarantee_kafka_1
2. docker network connect kafka-delivery-guarantee_kafka_network kafka-delivery-guarantee_kafka_1

## Expected Results
1. At Least Once: The count of received messages should be greater than the count of produced messages.
2. At Most Once: The count of received messages should be less than the count of produced messages.
3. Exactly Once: The count of received messages should be exactly the same as the count of produced messages.