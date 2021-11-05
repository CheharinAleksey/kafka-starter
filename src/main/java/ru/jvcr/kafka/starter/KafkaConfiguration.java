package ru.jvcr.kafka.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfiguration {

    @Bean
    public KafkaConsumerConfig kafkaConsumerConfig() {
        return new KafkaConsumerConfig();
    }

    @Bean
    public KafkaProducerConfig kafkaProducerConfig() {
        return new KafkaProducerConfig();
    }

    @Bean
    public KafkaSecurityService kafkaSecurityService() {
        return new KafkaSecurityService();
    }

    @Bean
    public KafkaSecurityProperty kafkaSecurityProperty() {
        return new KafkaSecurityProperty();
    }
}
