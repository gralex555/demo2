package com.alexey.order.service;

import com.alexey.order.DTO.FileMetaData;
import com.alexey.order.DTO.FileValidationResponse;
import com.alexey.order.DTO.Kafka.FileMetaDataDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class FileValidationChain {
    private final List<FileValidator> validators;
    private final SenderService senderService;
    private final ObjectMapper objectMapper;   //

    public void validate(MultipartFile file, boolean strictMode) throws IOException {
        log.info("Start Validation for file" + file.getOriginalFilename());
        for(FileValidator fileValidator:validators) {
            log.info("Валидация в сервисе" + validators.getClass().getName());
            fileValidator.validateFile(file, strictMode);
        }
        log.info("Успешное заверешение валидации");
        UUID randomId = UUID.randomUUID();
        FileMetaDataDTO fileMetaDataDTO = new FileMetaDataDTO(randomId, file.getName(), file.getContentType(), file.getSize(), file.getBytes(), Instant.now());
        senderService.sendKafkaMessage("storage-service", objectMapper.writeValueAsString(fileMetaDataDTO));
    }


}
