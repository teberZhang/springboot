package com.example.springboot.common;

/**
 * 结果封装类
 */
public class BaseResult<T> {

    private Integer code = Constants.E20000;
    private String msg;
    private T data;
    private Integer count;

    public BaseResult() {
    }

    public BaseResult(Integer code, String msg, T data, Integer count) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    public Integer getCode() {
        return code;
    }

    public BaseResult<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BaseResult<T> setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public BaseResult<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public BaseResult<T> setCount(Integer count) {
        this.count = count;
        return this;
    }

    public static <T> BaseResult<T> errorMsg(Integer code, String msg, T data, Integer count) {
        BaseResult<T> result = new BaseResult<>(code, msg, data, count);
        if (null == result.code) {
            result.setCode(Constants.E99999);
        }
        return result;
    }

    public static <T> BaseResult<T> errorMsg(Integer code, String msg, Integer count) {
        return errorMsg(code, msg, null, count);
    }

    public static <T> BaseResult<T> errorMsg(Integer code, String msg) {
        return errorMsg(code, msg, null);
    }

    public static <T> BaseResult<T> errorMsg(String msg) {
        return errorMsg(Constants.E20050, msg);
    }

    public static <T> BaseResult<T> ok(T data, Integer count) {
        return new BaseResult<T>(Constants.E20000, "SUCCESS", data, count);
    }

    public static <T> BaseResult<T> ok(String msg, T data) {
        return new BaseResult<T>(Constants.E20000, msg, data, null);
    }

    public static <T> BaseResult<T> ok(T data) {
        return ok("SUCCESS", data);
    }

    public static <T> BaseResult<T> ok() {
        return ok(null);
    }

    public boolean tOrE() {
        return Constants.E20000.equals(this.getCode());
    }

    public boolean okAndHasData() {
        return Constants.E20000.equals(this.code) && null != this.data;
    }

    public boolean isOk() {
        return Constants.E20000.equals(this.code);
    }

}
