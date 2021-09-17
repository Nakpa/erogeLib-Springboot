package com.library.eroge.erogelib.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * tt_blog_tag
 * @author 
 */
@Data
@TableName("tt_blog_tag")
public class TtBlogTagPO implements Serializable {
    private Long tagId;

    /**
     * 博文ID
     */
    private Long blogId;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 文章标签创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createDate;

    private static final long serialVersionUID = 1L;
}