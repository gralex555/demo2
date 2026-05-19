package com.alexey.order.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.ByteArraySerializer;


import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.UUIDSerializer;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-service}")
    private String bootstrapService;

    @Bean
    public ProducerFactory<Long, byte[]> producerFactory() {
        Map<String, Object> map = new HashMap<>();
        map.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapService);
        map.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        map.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "trx-");

        return new DefaultKafkaProducerFactory<>(map, new LongSerializer(), new ByteArraySerializer());
    }

    @Bean
    public KafkaTemplate<Long, byte[]> kafkaTemplate(){
        KafkaTemplate<Long, byte[]> template = new KafkaTemplate<>(producerFactory(), kafkaTransactionManager());

        return template;
    }

    @Bean
    public KafkaTransactionManager<Long, byte[]> kafkaTransactionManager(ProducerFactory<Long, byte[]> producerFactory) {
        return new KafkaTransactionManager<>(producerFactory);
    }
// чтобы данные отправились гарантированно

}

