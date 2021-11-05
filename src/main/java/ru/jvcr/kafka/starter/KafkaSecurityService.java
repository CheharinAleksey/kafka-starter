package ru.jvcr.kafka.starter;

import org.apache.kafka.common.config.SslConfigs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;

@Component
public class KafkaSecurityService {
    private static final String SECURITY_PROTOCOL = "security.protocol";

    @Autowired
    private KafkaSecurityProperty kafkaSecurityProperty;

    public void setSecurityProps(Map<String, Object> props) {
        setProp(props, SECURITY_PROTOCOL, kafkaSecurityProperty.getSecurityProtocol());

        // При securityProtocol = "SSL" обязательны:
        setSslEndPointIdentificationAlgorithm(props);
        setProp(props, SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, kafkaSecurityProperty.getSslTruststoreLocation());
        setProp(props, SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, kafkaSecurityProperty.getSslTruststorePassword());
        setProp(props, SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, kafkaSecurityProperty.getSslTruststoreLocation());
        setProp(props, SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, kafkaSecurityProperty.getSslKeystorePassword());
        setProp(props, SslConfigs.SSL_KEY_PASSWORD_CONFIG, kafkaSecurityProperty.getSslKeyPassword());

        // При securityProtocol = "SSL" необязательны:
        setProp(props, SslConfigs.SSL_KEYSTORE_TYPE_CONFIG, kafkaSecurityProperty.getSslKeystoreType());
        setProp(props, SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, kafkaSecurityProperty.getSslTruststoreType());
    }

    private void setSslEndPointIdentificationAlgorithm(Map<String, Object> props) {
        // The endpoint identification algorithm used by clients to validate server host name.
        // The default value is https. Clients including client connections created by the broker
        // for inter-broker communication verify that the broker host name matches the host name
        // in the broker’s certificate. Disable server host name verification by setting
        // ssl.endpoint.identification.algorithm to an empty string.
        if (Objects.nonNull(kafkaSecurityProperty.getSslEndpointIdentificationAlgorithm())) {
            props.put(SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, kafkaSecurityProperty.getSslEndpointIdentificationAlgorithm());
        }
    }

    private void setProp(Map<String, Object> props, String key, String value) {
        if (!StringUtils.isEmpty(value)) {
            props.put(key, value);
        }
    }
}
