package com.library.eroge.erogelib.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BlogTagsDTO {
    Long blogId;

    String blogTitle;

    String imgUrl;

    String blogContent;

    Integer blogStatus;

    String createBy;

    String createByName;

    Date createDate;

    String updateBy;

    String updateByName;

    Date updateDate;

    String remark;

    String tagId;

    String tagName;

    String tagNames;

    String tagIds;
}
