package com.laughing.crowd.exception;
//未登录异常类
public class AccessForbiddenException extends RuntimeException {
    public AccessForbiddenException(String message) {
        super(message);
    }
}
