package com.ms.login.exceptions;

public class NotFoundUser extends RuntimeException{
    public NotFoundUser(String message) {
        super(message);
    }

    public NotFoundUser(String message, Throwable cause) {
        super(message, cause);
    }
}
