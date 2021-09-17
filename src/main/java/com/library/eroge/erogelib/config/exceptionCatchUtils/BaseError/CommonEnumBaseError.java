package com.library.eroge.erogelib.config.exceptionCatchUtils.BaseError;

public enum CommonEnumBaseError implements BaseErrorInfoInterface {

    // 数据操作错误定义
    SUCCESS("200", "请求成功!"),

    BODY_NOT_MATCH("400","请求的数据出现错误!"),

    SIGNATURE_NOT_MATCH("401","请求的数字签名不匹配!"),

    NOT_ACCESS_MATCH("403", "用户未得到授权!"),

    NOT_FOUND("404", "未找到该资源!"),

    INTERNAL_SERVER_ERROR("500", "服务器内部错误!"),

    GATEWAY_ERROR("502","网关错误!"),

    SERVER_BUSY("503","服务器正忙，请稍后再试!"),

    SERVER_TIME_OUT("504","网关超时!"),

    ERROR_PASSWORD("1001", "输入的原密码与账号当前密码不匹配"),

    ERROR_NO_USERINFO("1002", "发出的请求未获取到登录信息"),

    ERROR_TOEKN_VERIFY("50000", "token认证失败!");


    /** 错误码 */
    private String resultCode;

    /** 错误描述 */
    private String resultMsg;

    CommonEnumBaseError(String resultCode, String resultMsg) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    @Override
    public String getResultCode() {
        return resultCode;
    }

    @Override
    public String getResultMsg() {
        return resultMsg;
    }
}
