package com.library.eroge.erogelib.service.blog;

import com.library.eroge.erogelib.dto.BlogTagsDTO;
import com.library.eroge.erogelib.dto.UserTagsDTO;

import java.util.List;

public interface BlogService {
    List<BlogTagsDTO> queryBlogList();

    List<UserTagsDTO> queryUserTags();

    BlogTagsDTO saveBlog(BlogTagsDTO blogTagsDTO);
}
