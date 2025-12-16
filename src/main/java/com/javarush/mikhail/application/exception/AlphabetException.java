package com.javarush.mikhail.application.exception;

public class AlphabetException extends ApplicationException {
    public AlphabetException(String message) {
        super(message);
    }

    public AlphabetException(String message, Throwable cause) {
        super(message, cause);
    }
}
