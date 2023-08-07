package com.watson.noticeconsumer.exception;

public class FCMException extends RuntimeException{
    public FCMException(String message) {
        super(message);
    }

    public FCMException(String message, Throwable cause) {
        super(message, cause);
    }
}
