package com.alexey.order.DTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
public class FileValidationResponse {
    private String status;
    private String fieldId;
    private FileMetaData metaData;
    private String message;
    private List<String> errors;
}
