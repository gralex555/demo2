package com.alexey.order.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class KafkaHandler {

    @KafkaListener(topics = "storage-service", groupId = "group")
    public void handleFileEvent(String fileName) {
        System.out.println("Пришло сообщение от Kafka" + fileName);
    }
}
