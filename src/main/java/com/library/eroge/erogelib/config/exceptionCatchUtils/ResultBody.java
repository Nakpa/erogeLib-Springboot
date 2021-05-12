package com.library.eroge.erogelib.config.exceptionCatchUtils;


import com.alibaba.fastjson.JSONObject;
import com.library.eroge.erogelib.config.exceptionCatchUtils.BaseError.BaseErrorInfoInterface;
import com.library.eroge.erogelib.config.exceptionCatchUtils.BaseError.CommonEnumBaseError;
import lombok.Builder;

import java.io.Serializable;

@Builder
public class ResultBody implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 响应代码
     */
    private String code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应结果
     */
    private Object result;

    public ResultBody() {
    }

    public ResultBody(BaseErrorInfoInterface errorInfo) {
        this.code = errorInfo.getResultCode();
        this.message = errorInfo.getResultMsg();
    }

    public ResultBody(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.result = data;
    }

    public ResultBody(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    /* *
     * 成功
     * @return
     */
    public static ResultBody success() {
//        ResultBody result = new ResultBody();
//        result.setCode(CommonEnumBaseError.SUCCESS.getResultCode());
//        result.setMessage(CommonEnumBaseError.SUCCESS.getResultMsg());
//        result.setResult(null);
//        return result;
        return ResultBody.builder()
                .code(CommonEnumBaseError.SUCCESS.getResultCode())
                .message(CommonEnumBaseError.SUCCESS.getResultMsg())
                .result(null).build();
    }

    /* *
     * 成功
     * @param data
     * @return
     */
    public static ResultBody success(Object data) {
//        ResultBody result = new ResultBody();
//        result.setCode(CommonEnumBaseError.SUCCESS.getResultCode());
//        result.setMessage(CommonEnumBaseError.SUCCESS.getResultMsg());
//        result.setResult(data);
//        return result;
        return ResultBody.builder()
                .code(CommonEnumBaseError.SUCCESS.getResultCode())
                .message(CommonEnumBaseError.SUCCESS.getResultMsg())
                .result(data).build();
    }

    /**
     * 失败
     */
    public static ResultBody error(BaseErrorInfoInterface errorInfo) {
        ResultBody result = new ResultBody();
        result.setCode(errorInfo.getResultCode());
        result.setMessage(errorInfo.getResultMsg());
        result.setResult(null);
        return result;
    }

    /**
     * 失败
     */
    public static ResultBody error(String code, String message) {
        ResultBody result = new ResultBody();
        result.setCode(code);
        result.setMessage(message);
        result.setResult(null);
        return result;
    }

    /**
     * 失败
     */
    public static ResultBody error(String message) {
        ResultBody result = new ResultBody();
        result.setCode("500");
        result.setMessage(message);
        result.setResult(null);
        return result;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

}
