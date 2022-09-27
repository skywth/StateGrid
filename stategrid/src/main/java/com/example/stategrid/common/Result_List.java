package com.example.stategrid.common;

import java.util.List;
import java.util.Map;

public class Result_List {

    private static final int SUCCESS_CODE = 0;
    private static final int ERROR_CODE = 1;
    private Integer code;
    private String message = "";
    private List data;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public static Result_List success(List data) {
        Result_List result = new Result_List();
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        return result;
    }

    public static Result_List error(String message) {
        Result_List result = new Result_List();
        result.setCode(ERROR_CODE);
        result.setMessage(message);
        return result;
    }
}
