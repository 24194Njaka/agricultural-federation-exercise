package com.argiculturre.exception;


             public class GlobalExceptionHandler {
    public static RuntimeException notFound(String message) {
        return new RuntimeException(message);
    }

    public static RuntimeException conflict(String message) {
        return new RuntimeException(message);
    }
}
