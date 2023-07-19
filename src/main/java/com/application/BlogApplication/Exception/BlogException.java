package com.application.BlogApplication.Exception;

import org.springframework.http.HttpStatus;

public class BlogException extends RuntimeException {
    private final HttpStatus status;

    public BlogException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}