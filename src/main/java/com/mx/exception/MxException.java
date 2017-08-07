package com.mx.exception;

/**
 * 业务异常
 */
public class MxException extends  RuntimeException {

    public MxException(){

    }

    public MxException(String msg){
        super(msg);
    }
}
