package com.eshore.demo.common;

import java.io.Serializable;

/**
 * @author eshore
 */
public class ResultJson implements Serializable {

    private String code;
    private Object responseEntity;

    public static final String SUCCESS_CODE = "666";

    public static final String ERROR_CODE = "999";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getResponseEntity() {
        return responseEntity;
    }

    public void setResponseEntity(Object responseEntity) {
        this.responseEntity = responseEntity;
    }

    public ResultJson() {
    }

    public ResultJson(String code, Object responseEntity) {
        this.code = code;
        this.responseEntity = responseEntity;
    }
}
