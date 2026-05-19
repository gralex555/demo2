package com.alexey.order.service;

import com.alexey.order.DTO.Kafka.FileMetaDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.Instant;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SenderService {

   final KafkaTemplate<Long, byte[]> test;
   final ObjectMapper objectMapper;
   final Random random = new Random();

   @Transactional
   public void sendFile(MultipartFile dto) throws IOException {
       FileMetaDataDTO fileMetaDataDTO = new FileMetaDataDTO(random.nextLong(),
               dto.getOriginalFilename(),
               dto.getContentType(),
               dto.getSize(),
               dto.getBytes(), Instant.now());

       byte[] jsonBytes = objectMapper.writeValueAsBytes(fileMetaDataDTO);
       System.out.println("Сообщение отправлено" + fileMetaDataDTO);

       test.send("file-metadataTopic", fileMetaDataDTO.fileId(), jsonBytes);
   }

//    public void sendKafkaMessage(String kafkaTopic, String message) {
//        test.send(kafkaTopic, message);
//
//    }
}
