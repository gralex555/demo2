package com.alexey.order.model;

import org.apache.kafka.common.protocol.types.Field;

import java.time.Instant;
import java.util.UUID;

public record FileMetaDataDTO(Long fileId,
                              String fileName,
                              String contentType,
                              Long fileSize,
                              byte[]content,
                              Instant upload) {
}