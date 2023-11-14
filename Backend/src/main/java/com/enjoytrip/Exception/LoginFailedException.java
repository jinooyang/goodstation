package com.enjoytrip.Exception;

public class LoginFailedException extends Throwable {
    public LoginFailedException(Exception e) {
        super(e.getMessage());
    }
}
