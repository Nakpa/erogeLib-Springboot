package com.library.eroge.erogelib.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * tt_operate_log
 * @author 
 */
@Data
@TableName("TT_OPERATE_LOG")
public class TtOperateLogPO implements Serializable {
    private Long logId;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作类型
     */
    private Integer operateType;

    /**
     * 操作内容
     */
    private String operateContent;

    /**
     * 操作时间
     */
    private Date operateDate;

    private Date createDate;

    private Date updateDate;

    private static final long serialVersionUID = 1L;
}