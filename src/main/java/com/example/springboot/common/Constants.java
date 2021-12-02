package com.example.springboot.common;

public class Constants {

    //异常编码
    public static final Integer E20000 = 20000; // 操作成功
    public static final Integer E99999 = 99999; // 系统异常 Exception
    public static final Integer E50000 = 50000; // 运行时异常 RuntimeException、ConversionNotSupportedException、HttpMessageNotWritableException
    public static final Integer E50001 = 50001; // 运行时异常 ArithmeticException
    public static final Integer E50002 = 50002; // 运行时异常 NullPointerException
    public static final Integer E50003 = 50003; // 运行时异常 ClassCastException
    public static final Integer E50004 = 50004; // 运行时异常 IOException
    public static final Integer E50005 = 50005; // 运行时异常 NoSuchMethodException
    public static final Integer E50006 = 50006; // 运行时异常 IndexOutOfBoundsException
    public static final Integer E50007 = 50007; // 运行时异常 HttpMessageNotReadableException
    public static final Integer E50008 = 50008; // 运行时异常 TypeMismatchException
    public static final Integer E50009 = 50009; // 运行时异常 MissingServletRequestParameterException
    public static final Integer E50010 = 50010; // 运行时异常 HttpRequestMethodNotSupportedException
    public static final Integer E50011 = 50011; // 运行时异常 HttpMediaTypeNotAcceptableException
    public static final Integer E50012 = 50012; // 运行时异常 StackOverflowError
    public static final Integer E50013 = 50013; // 参数校验错误 MethodArgumentNotValidException

    // 自定义异常CustomException相关
    public static final Integer E20050 = 20050; // 自定义异常默认显示的状态编码

    public static final Integer E40001 = 40001;

    // 分页常量 全局统一使用
    public static final Integer DEFAULT_PAGE = 1; // 默认第一页
    public static final Integer DEFAULT_PAGE_SIZE = 10; // 默认每页10条
}
