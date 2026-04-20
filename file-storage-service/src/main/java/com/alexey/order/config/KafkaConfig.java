package com.alexey.order.config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.kafka.autoconfigure.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import tools.jackson.databind.deser.jdk.StringDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

   @Value("${spring.kafka.bootstrap-service}")
    private String bootstrapService;

   @Bean
   public ConsumerFactory<String, String> consumerFactory() {
       Map<String, Object> probs = new HashMap<String, Object>();
       probs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapService);
       probs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
       probs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
       probs.put(ConsumerConfig.GROUP_ID_CONFIG, "group");

       return new DefaultKafkaConsumerFactory<>(probs);
   }

   @Bean
   public ConcurrentKafkaListenerContainerFactory<String, String> concurrentKafkaListenerContainer() {
       ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
       factory.setConsumerFactory(consumerFactory());
       return factory;
    }

}
