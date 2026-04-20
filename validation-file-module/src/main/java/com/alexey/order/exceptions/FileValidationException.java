package com.alexey.order.exceptions;

public class FileValidationException extends RuntimeException {

    public FileValidationException(String message) {
        super(message);
    }
}
