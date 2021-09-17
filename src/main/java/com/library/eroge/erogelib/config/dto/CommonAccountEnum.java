package com.library.eroge.erogelib.config.dto;

public enum CommonAccountEnum implements CommonAccountInterface {

    ADMIN_ACCOUNT("admin","erogelibAdmin"),

    TOURIST_ACCOUNT("tourist","erogelibTourist");


    /** 错误码 */
    private String account;

    /** 错误描述 */
    private String userId;

    CommonAccountEnum(String account, String userId) {
        this.account = account;
        this.userId = userId;
    }

    @Override
    public String getAccount() {
        return account;
    }

    @Override
    public String getUserId() {
        return userId;
    }
}
