package com.example.jwtdemo.exception;

import org.springframework.security.core.AuthenticationException;

public class WrongTokenFormatException extends AuthenticationException {

    public WrongTokenFormatException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public WrongTokenFormatException(String msg) {
        super(msg);
    }
}
