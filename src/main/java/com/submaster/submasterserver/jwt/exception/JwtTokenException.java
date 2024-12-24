package com.submaster.submasterserver.jwt.exception;

import lombok.Getter;

@Getter
public class JwtTokenException extends RuntimeException{
    private final String message;

    public JwtTokenException(String message, Exception e) {
        super(message, e);
        this.message = message;
    }

    public JwtTokenException(String message) {
        this.message = message;
    }
}
