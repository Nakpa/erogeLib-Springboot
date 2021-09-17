package com.library.eroge.erogelib.config.dto;



import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.Serializable;

@Component
@Scope("request")
public class LoginInfoDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    // 用户ID
    String userId;

    // 联系电话
    String telPhone;

    // 电子邮箱
    String email;

    // 用户名
    String userName;

    // 用户账号
    String userAccount;

    public LoginInfoDTO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTelPhone() {
        return telPhone;
    }

    public void setTelPhone(String telPhone) {
        this.telPhone = telPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
