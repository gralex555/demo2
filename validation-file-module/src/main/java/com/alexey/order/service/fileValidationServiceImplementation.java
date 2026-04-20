package com.alexey.order.service;

import com.alexey.order.DTO.FileValidationResponse;
import org.springframework.web.multipart.MultipartFile;

public class fileValidationServiceImplementation implements FileValidationService {

    @Override
    public FileValidationResponse validateFile(MultipartFile multipartFile, boolean strictMode) {
        return null;
    }
}
