package com.alexey.order.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.UUIDDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.kafka.autoconfigure.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

   @Value("${spring.kafka.bootstrap-service}")
    private String bootstrapService;

   @Bean
   public ConsumerFactory<Long, byte[]> consumerFactory() {
       Map<String, Object> probs = new HashMap<String, Object>();
       probs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapService);
       probs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
       probs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
       probs.put(ConsumerConfig.GROUP_ID_CONFIG, "group");
       probs.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_commited");
       probs.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
       probs.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

       return new DefaultKafkaConsumerFactory<>(probs);
   }

   @Bean
   public ConcurrentKafkaListenerContainerFactory<Long, byte[]> concurrentKafkaListenerContainer() {
       ConcurrentKafkaListenerContainerFactory<Long, byte[]> factory = new ConcurrentKafkaListenerContainerFactory<>();
       factory.setConsumerFactory(consumerFactory());

       factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
       return factory;
    }

}
