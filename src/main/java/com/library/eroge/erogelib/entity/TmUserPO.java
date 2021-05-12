package com.library.eroge.erogelib.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * tm_user
 * @author 
 */
@Data
@TableName("TM_USER")
public class TmUserPO implements Serializable {
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 登入账号
     */
    private String userAccount;

    /**
     * 登入密码
     */
    private String accoPassword;

    /**
     * 用户头像(图像文件ID)
     */
    private String userIcon;

    /**
     * 联系电话
     */
    private String telPhone;

    /**
     * 联系邮箱
     */
    private String email;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 修改时间
     */
    private Date updateDate;

    private static final long serialVersionUID = 1L;
}