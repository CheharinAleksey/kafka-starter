package ru.jvcr.kafka.starter;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Autowired
    private KafkaSecurityService kafkaSecurityService;

    @Value("${frkk.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value(value = "${frkk.kafka.group-id}")
    private String groupId;

    @Value("${frkk.kafka.consumer.key-deserializer:org.apache.kafka.common.serialization.StringDeserializer}")
    private Class<?> keyDeserializer;
    @Value("${frkk.kafka.consumer.value-deserializer:org.springframework.kafka.support.serializer.JsonDeserializer}")
    private Class<?> valueDeserializer;

    private Map<String, Object> props;

    @Bean
    public ConsumerFactory<String, Map<String, Object>> kafkaConsumer() {
        props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeserializer);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeserializer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        kafkaSecurityService.setSecurityProps(props);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public KafkaListenerContainerFactory kafkaContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Map<String, Object>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(kafkaConsumer());
        return factory;
    }
}
