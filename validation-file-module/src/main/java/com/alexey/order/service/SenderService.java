package com.alexey.order.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SenderService {

   final KafkaTemplate<String, String> test;

    public void sendKafkaMessage(String kafkaTopic, String message) {
        test.send(kafkaTopic, message);

    }
}
