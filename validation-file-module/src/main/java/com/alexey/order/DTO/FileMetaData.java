package com.alexey.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class FileMetaData {
    private String fileName;
    private Long size;
    private String mimiType;
    private String signature;
}
