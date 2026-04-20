package com.alexey.order.DTO.Kafka;

import java.time.Instant;
import java.util.UUID;

public record FileMetaDataDTO(UUID fileId, String fileName, String contentType, Long fileSize, byte[]content, Instant upload) {
}
