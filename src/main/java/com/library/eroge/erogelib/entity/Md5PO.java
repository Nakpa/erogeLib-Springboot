package com.library.eroge.erogelib.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * md5
 * @author 
 */
@Data
@TableName("MD5")
public class Md5PO implements Serializable {
    private Long indexId;

    private String md5;

    /**
     * 是否有效
     */
    private Integer isValid;

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