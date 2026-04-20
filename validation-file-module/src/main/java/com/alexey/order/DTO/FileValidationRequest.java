package com.alexey.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class FileValidationRequest {
    private boolean strictMode;
    // проверка файла
}
