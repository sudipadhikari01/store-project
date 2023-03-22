package com.sudip.store.electronicstore.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String s) {
        super(s);
    }

    public BadRequestException() {
        super("Provided file format not accepted");
    }
}
