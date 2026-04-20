package com.alexey.order.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
@Entity
public class FileValidationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private Long fileSize;
    private String mimiType;
    private String extensions;
    private String status;
    private String errorCode;
    private String clientIP;
    private LocalDateTime createdDate;

    public FileValidationLog(LocalDateTime createdDate, String clientIP, String errorCode, String status, String extensions, String mimiType, Long fileSize, String fileName, Long id) {
        this.createdDate = createdDate;
        this.clientIP = clientIP;
        this.errorCode = errorCode;
        this.status = status;
        this.extensions = extensions;
        this.mimiType = mimiType;
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.id = id;
    }

    public FileValidationLog() {

    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public String getMimiType() {
        return mimiType;
    }

    public String getExtensions() {
        return extensions;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getClientIP() {
        return clientIP;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public void setMimiType(String mimiType) {
        this.mimiType = mimiType;
    }

    public void setExtensions(String extensions) {
        this.extensions = extensions;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}