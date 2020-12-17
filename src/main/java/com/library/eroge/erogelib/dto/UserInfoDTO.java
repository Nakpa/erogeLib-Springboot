package com.library.eroge.erogelib.dto;

import lombok.Data;

@Data
public class UserInfoDTO {
    // 用户ID
    String userId;

    // 用户密码
    String password;

    // 原密码（修改时用来校验）
    String oldPassword;

    // 联系电话
    String telPhone;

    // 电子邮箱
    String email;

    // 用户名
    String userName;

    // 用户账号
    String userAccount;
}
