package com.example.springboot.common.exception;

public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Integer code;

    /*无参构造函数*/
    public CustomException() {
        super();
    }

    //用详细信息指定一个异常
    public CustomException(String message) {
        super(message);
    }

    //指定一个自定义code的异常
    public CustomException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    //用指定的详细信息和原因构造一个新的异常
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    //用指定的详细信息和原因构造一个新的异常
    public CustomException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    //用指定原因构造一个新的异常
    public CustomException(Throwable cause) {
        super(cause);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
