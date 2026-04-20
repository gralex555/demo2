package com.alexey.order.validation;

import com.alexey.order.exceptions.FileValidationException;
import com.alexey.order.service.FileValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ContentValidator implements FileValidator {
    private final Tika tika;

    public ContentValidator(Tika tika) {
        this.tika = tika;
    }

    @Override
    public void validateFile(MultipartFile multipartFile, boolean strictMode) {
        try {
            String detectedType = tika.detect(multipartFile.getInputStream());
            String actual = multipartFile.getContentType();
            log.debug("Проверка на внутреннее совпадение");
            if (detectedType != null && !detectedType.equals(actual)) {
                log.warn("Найдены отличия по байтам");
                throw new FileValidationException("ContentValidationException");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("Проверка на внутреннее совпадение прошла успешно");

    }


}

