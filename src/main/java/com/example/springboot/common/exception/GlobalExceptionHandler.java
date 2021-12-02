package com.example.springboot.common.exception;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.example.springboot.common.BaseResult;
import com.example.springboot.common.Constants;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ValidationException;
import java.io.IOException;

/**
 * 全局异常拦截器
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static Log log = LogFactory.get(GlobalExceptionHandler.class);

    private static final String logExceptionFormat = "全局异常捕获{Code: %s, Detail: %s}";

    //自定义异常CustomException
    @ExceptionHandler(CustomException.class)
    public BaseResult<?> arithmeticException(CustomException ce) {
        return customExFormat(ce.getCode(), ce);
    }

    @ExceptionHandler(MyBatisSystemException.class)
    public BaseResult<?> sqlTimeOutException() {
        return BaseResult.errorMsg(Constants.E50001, "数据库连接失败");
    }

    //除数不能为0
    @ExceptionHandler(ArithmeticException.class)
    public BaseResult<?> arithmeticException(ArithmeticException ex) {
        return resultFormat(Constants.E50001, ex);
    }

    //空指针异常
    @ExceptionHandler(NullPointerException.class)
    public BaseResult<?> nullPointerExceptionHandler(NullPointerException ex) {
        return resultFormat(Constants.E50002, ex);
    }

    //类型转换异常
    @ExceptionHandler(ClassCastException.class)
    public BaseResult<?> classCastExceptionHandler(ClassCastException ex) {
        return resultFormat(Constants.E50003, ex);
    }

    //IO异常
    @ExceptionHandler(IOException.class)
    public BaseResult<?> iOExceptionHandler(IOException ex) {
        return resultFormat(Constants.E50004, ex);
    }

    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)
    public BaseResult<?> noSuchMethodExceptionHandler(NoSuchMethodException ex) {
        return resultFormat(Constants.E50005, ex);
    }

    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)
    public BaseResult<?> indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {
        return resultFormat(Constants.E50006, ex);
    }

    //400错误
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public BaseResult<?> requestNotReadable(HttpMessageNotReadableException ex) {
        return resultFormat(Constants.E50007, ex);
    }

    //400错误
    @ExceptionHandler(TypeMismatchException.class)
    public BaseResult<?> requestTypeMismatch(TypeMismatchException ex) {
        return resultFormat(Constants.E50008, ex);
    }

    //400错误
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public BaseResult<?> requestMissingServletRequest(MissingServletRequestParameterException ex) {
        return resultFormat(Constants.E50009, ex);
    }

    //405错误
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResult<?> request405(HttpRequestMethodNotSupportedException ex) {
        return resultFormat(Constants.E50010, ex);
    }

    //406错误
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public BaseResult<?> request406(HttpMediaTypeNotAcceptableException ex) {
        return resultFormat(Constants.E50011, ex);
    }

    //栈溢出
    @ExceptionHandler(StackOverflowError.class)
    public BaseResult<?> requestStackOverflow(StackOverflowError ex) {
        return resultFormat(Constants.E50012, ex);
    }

    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    public BaseResult<?> server500(RuntimeException ex) {
        return runtimeExceptionHandler(ex);
    }

    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    public BaseResult<?> runtimeExceptionHandler(RuntimeException ex) {
        return resultFormat(Constants.E50000, ex);
    }

    //其他错误
    @ExceptionHandler(Exception.class)
    public BaseResult<?> exception(Exception ex) {
        return resultFormat(Constants.E99999, ex);
    }


    //参数校验异常MethodArgumentNotValidException、ValidationException
    @ExceptionHandler({MethodArgumentNotValidException.class, ValidationException.class})
    public BaseResult<?> violationException(Exception ce) {
        if (ce instanceof MethodArgumentNotValidException){
            BindingResult bindingResult = ((MethodArgumentNotValidException) ce).getBindingResult();
            if (bindingResult.hasErrors()){
                return customExFormat(Constants.E50013, new CustomException(Constants.E50013, bindingResult.getAllErrors().get(0).getDefaultMessage()));
            }
        } else if (ce instanceof ValidationException){
            return customExFormat(Constants.E50013, new CustomException(Constants.E50013, ce.getMessage()));
        }
        return customExFormat(Constants.E50013, new CustomException(Constants.E50013, ce.getMessage()));
    }

    /**
     * 常规系统异常调用该方法
     *
     * @param code 异常code
     * @param ex   异常
     * @param <T>  异常类型限制
     * @return 结果
     */
    private <T extends Throwable> BaseResult<?> resultFormat(Integer code, T ex) {
        log.error(ex, String.format(logExceptionFormat, code, ex.getMessage()));
        return BaseResult.errorMsg(code,  ex.getMessage());
    }

    /**
     * 自定义可控制异常调用该方法
     *
     * @param code 异常code
     * @param ex   异常
     * @param <T>  异常类型限制
     * @return 结果
     */
    private <T extends Throwable> BaseResult<?> customExFormat(Integer code, T ex) {
        log.error(ex, String.format(logExceptionFormat, code, ex.getMessage()));
        if (null == code) {
            return BaseResult.errorMsg(ex.getMessage());
        }
        return BaseResult.errorMsg(code, ex.getMessage());
    }
}
