package com.submaster.submasterserver.api.exception;

import lombok.Getter;

@Getter
public class SubMasterException extends RuntimeException {

    private final String message;

    public SubMasterException(String message, Exception e) {
        super(message, e);
        this.message = message;
    }

    public SubMasterException(String message){
        this.message = message;
    }
}
