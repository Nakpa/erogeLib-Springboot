package com.library.eroge.erogelib.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * tt_blog
 * @author 
 */
@Data
@TableName("tt_blog")
public class TtBlogPO implements Serializable {

    @TableId(value = "BLOG_ID", type = IdType.AUTO)
    private Long blogId;

    /**
     * 文章标题
     */
    @TableId(value = "BLOG_TITLE")
    private String blogTitle;

    /**
     * 文章标题
     */
    @TableId(value = "IMG_URL")
    private String imgUrl;

    /**
     * 文章内容
     */
    @TableId(value = "BLOG_CONTENT")
    private String blogContent;

    /**
     * 文章状态
     */
    @TableId(value = "BLOG_STATUS")
    private Integer blogStatus;

    /**
     * 创建人
     */
    @TableId(value = "CREATE_BY")
    private String createBy;

    /**
     * 创建人昵称
     */
    @TableId(value = "CREATE_BY_NAME")
    private String createByName;

    /**
     * 创建时间
     */
    @TableId(value = "CREATE_DATE")
    private Date createDate;

    /**
     * 修改人
     */
    @TableId(value = "UPDATE_BY")
    private String updateBy;

    /**
     * 创建人昵称
     */
    @TableId(value = "UPDATE_BY_NAME")
    private String updateByName;

    /**
     * 修改时间
     */
    @TableId(value = "UPDATE_DATE")
    private Date updateDate;

    /**
     * 备注
     */
    @TableId(value = "REMARK")
    private String remark;

    private static final long serialVersionUID = 1L;
}