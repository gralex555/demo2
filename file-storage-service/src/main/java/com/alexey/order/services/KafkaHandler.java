package com.alexey.order.services;

import com.alexey.order.model.FileMetaDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KafkaHandler {

    private final ObjectMapper objectMapper;

    //   @KafkaListener(topics = "file-metadataTopic", groupId = "group")
//    public void handleFileEvent(Long fileId, byte[] bytes) {
//        System.out.println("Пришло сообщение от Kafka" + fileId);
//        FileMetaDataDTO fileMetaDataDTO = objectMapper.readValue(bytes, FileMetaDataDTO.class);
//        System.out.println(fileMetaDataDTO + "fileId");
//
//    }


    @KafkaListener(topics = "file-metadataTopic", groupId = "group")
    public void handleFileEvent(String message, Acknowledgment acknowledgment) {
        System.out.println("Пришло сообщение от Kafka");
        FileMetaDataDTO fileMetaDataDTO = objectMapper.readValue(message, FileMetaDataDTO.class);
        System.out.println(fileMetaDataDTO);
        acknowledgment.acknowledge();  // говорим, что сообщение обработано

    }

}
