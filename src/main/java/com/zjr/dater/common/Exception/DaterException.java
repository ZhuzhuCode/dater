package com.zjr.dater.common.Exception;

/**
 * Created by zhujr on 2018/10/24.
 * 基础异常类
 */
public class DaterException extends RuntimeException {
    public DaterException(String message){
        super(message);
    }

    public DaterException(String message,Throwable casue){
        super(message,casue);
    }
}
