package ru.jvcr.kafka.starter;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    @Value("${frkk.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${frkk.kafka.producer.key-deserializer:org.apache.kafka.common.serialization.StringSerializer}")
    private Class<?> keySerializer;
    @Value("${frkk.kafka.producer.value-deserializer:org.springframework.kafka.support.serializer.JsonSerializer}")
    private Class<?> valueSerializer;

    @Autowired
    private KafkaSecurityService kafkaSecurityService;
    private Map<String, Object> props;

    @Bean
    public Map<String, Object> producerConfigs() {
        props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializer);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer);
        kafkaSecurityService.setSecurityProps(props);
        return props;
    }

    @Bean
    public <T> ProducerFactory<String, T> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }


    @Bean
    public <T> KafkaTemplate<String, T> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    public Map<String, Object> getProps() {
        return props;
    }
}
