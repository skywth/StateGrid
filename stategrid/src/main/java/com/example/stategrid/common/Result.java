package com.example.stategrid.common;

import com.example.stategrid.entity.test1_entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Result {
    private static final int SUCCESS_CODE = 0;
    private static final int ERROR_CODE = 1;

    private Integer code;
    private String message = "";
    private Map<String, Object> data;

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

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public static Result success(Map<String, Object> data) {
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        if(data!=null)
            result.setData(data);
        return result;
    }

    public static Result error(String message) {
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setMessage(message);
        return result;
    }
}