package com.alexey.order.validation;

import com.alexey.order.exceptions.FileValidationException;
import com.alexey.order.service.FileValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExtensionValidator implements FileValidator {
    @Value("${allowed.extension:pdf,docs,excel}")
    private String allowedExtensionConfig;  // сейчас эта переменаая = null;

    @Override
    public void validateFile(MultipartFile multipartFile, boolean strictMode){  // не используем strictMode ?
        String fileName = multipartFile.getOriginalFilename();  // получаем оригинально имя файла вместе с расширением
        if(fileName == null || fileName.isEmpty()) {
            throw new FileValidationException("Имя файла отсутствует или пустое");
        }
        String extension = getFileExtension(fileName);  // получаем расширения файла. Вызываем метод, созданный ниже.

        List<String> allowedExtension = allowedExtension();  // получаем список разрешенных расширений.

        if(!allowedExtension.contains(extension.toLowerCase())) {
            String message = "Такой файл не поддерживается";
          log.warn(message);
          throw new FileValidationException(message);
        }
        log.debug("extension validation passed");
    }

    private String getFileExtension(String fileName){   // метод извлекает расширение файла из имени файла
        int dotIndex = fileName.lastIndexOf(".");  // ищет последнюю точку в строке с конца и возвращает ее индекс.
        // если точки нет, то возвращает -1
        return dotIndex > 0 ? fileName.substring(dotIndex + 1): "";
    }

    private List<String> allowedExtension() {   //разбиваем строку allowedExtensionConfig по запятой и возвращаем список.
        return Arrays.asList(allowedExtensionConfig.split(","));
    }
}
