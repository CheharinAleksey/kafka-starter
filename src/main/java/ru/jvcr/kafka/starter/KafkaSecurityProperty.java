package ru.jvcr.kafka.starter;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class KafkaSecurityProperty {

    @Value("${frkk.kafka.security.protocol:}")
    private String securityProtocol;

    @Value("${frkk.kafka.ssl.endpoint.identification.algorithm:}")
    private String sslEndpointIdentificationAlgorithm;

    @Value("${frkk.kafka.ssl.truststore.location:}")
    private String sslTruststoreLocation;

    @Value("${frkk.kafka.ssl.truststore.password:}")
    private String sslTruststorePassword;

    @Value("${frkk.kafka.ssl.keystore.location:}")
    private String sslKeystoreLocation;

    @Value("${frkk.kafka.ssl.keystore.password:}")
    private String sslKeystorePassword;

    @Value("${frkk.kafka.ssl.key.password:}")
    private String sslKeyPassword;

    @Value("${frkk.kafka.ssl.keystore.type:}")
    private String sslKeystoreType;

    @Value("${frkk.kafka.ssl.truststore.type:}")
    private String sslTruststoreType;
}