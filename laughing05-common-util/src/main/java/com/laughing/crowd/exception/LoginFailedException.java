package com.laughing.crowd.exception;
//登录异常类
public class LoginFailedException extends RuntimeException {
    public LoginFailedException(String message) {
        super(message);
    }
}
