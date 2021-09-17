package com.library.eroge.erogelib.controller.blog;

import com.library.eroge.erogelib.config.exceptionCatchUtils.annotation.ResponseResult;
import com.library.eroge.erogelib.dto.BlogTagsDTO;
import com.library.eroge.erogelib.dto.UserInfoDTO;
import com.library.eroge.erogelib.dto.UserTagsDTO;
import com.library.eroge.erogelib.service.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@Transactional
@ResponseResult
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    // 查询用户所有blog
    @GetMapping("/queryBlogList")
    public List<BlogTagsDTO> queryBlogList() {
        return blogService.queryBlogList();
    }

    // 查询用户所有tag
    @GetMapping("/queryUserTags")
    public List<UserTagsDTO> queryUserTags() {
        return blogService.queryUserTags();
    }

    // 保存blog
    @PostMapping("/saveBlog")
    public BlogTagsDTO saveBlog(@RequestBody @Valid BlogTagsDTO blogTagsDTO) {
        return blogService.saveBlog(blogTagsDTO);
    }
}
