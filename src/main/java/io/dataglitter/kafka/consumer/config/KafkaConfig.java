package io.dataglitter.kafka.consumer.config;

import java.util.Map;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

/**
 * Created by reddys on 10/03/2018.
 */

@Configuration
@EnableKafka
public class KafkaConfig {

    private static final String SCHEMA_REGISTRY_URL_KEY = "schema.registry.url";

    @Value("${schema.registry.url}")
    private String schemaRegistryUrl;

    @Value("${kafka.bootstrap.servers}")
    private String bootstrapServers;

    private final KafkaProperties properties;

    @Autowired
    public KafkaConfig( KafkaProperties properties) {
        this.properties = properties;
    }

    @Bean
    public ConsumerFactory<?, ?> kafkaConsumerFactory() {
        Map<String, Object> consumerProperties = properties.buildConsumerProperties();
        consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        consumerProperties.put(SCHEMA_REGISTRY_URL_KEY, schemaRegistryUrl);
        return new DefaultKafkaConsumerFactory<Object, Object>(consumerProperties);
    }

}
