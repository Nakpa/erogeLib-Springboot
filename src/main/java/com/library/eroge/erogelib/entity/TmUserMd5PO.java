package com.library.eroge.erogelib.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * tm_user_md5
 * @author 
 */
@Data
@TableName("TM_USER_MD5")
public class TmUserMd5PO implements Serializable {
    private Integer indexId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 当前密码用到的MD5
     */
    private String md5;

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