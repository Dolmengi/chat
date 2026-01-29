package com.dolmengi.common.exception;

import lombok.Getter;

@Getter
public class ChatException extends RuntimeException {

    private final ExceptionCode error;

    public ChatException(ExceptionCode e) {
        super(e.getMessage());
        this.error = e;
    }

    public ChatException(ExceptionCode e, String message) {
        super(message);
        this.error = e;
    }

}
