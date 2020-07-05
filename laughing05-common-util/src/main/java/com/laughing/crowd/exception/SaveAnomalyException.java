package com.laughing.crowd.exception;
//未登录异常类
public class SaveAnomalyException extends RuntimeException {
    public SaveAnomalyException(String message) {
        super(message);
    }
}
