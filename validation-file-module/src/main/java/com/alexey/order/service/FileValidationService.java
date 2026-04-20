package com.alexey.order.service;

import com.alexey.order.DTO.FileValidationResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileValidationService {
    FileValidationResponse validateFile(MultipartFile multipartFile, boolean strictMode);
}
