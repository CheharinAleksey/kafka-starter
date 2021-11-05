package ru.jvcr.kafka.starter;

import org.springframework.kafka.core.KafkaTemplate;

public class BaseProducer<T> {
    private final KafkaTemplate<String, T> kafkaTemplate;
    private final String topicName;

    public BaseProducer(KafkaTemplate<String, T> kafkaTemplate, String topicName) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    public void produce(T event) {
        kafkaTemplate.send(topicName, event);
    }
}
