package com.javarush.mikhail.application.exception;

public class FileOperationException extends ApplicationException {
    public FileOperationException(String message) {
        super(message);
    }

    public FileOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
