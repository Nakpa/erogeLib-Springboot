package com.library.eroge.erogelib.service.blog;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.library.eroge.erogelib.config.components.ApplicationContextHelper;
import com.library.eroge.erogelib.config.dto.LoginInfoDTO;
import com.library.eroge.erogelib.dto.BlogTagsDTO;
import com.library.eroge.erogelib.dto.UserTagsDTO;
import com.library.eroge.erogelib.entity.TtBlogPO;
import com.library.eroge.erogelib.entity.TtBlogTagPO;
import com.library.eroge.erogelib.mapper.TtBlogPOMapper;
import com.library.eroge.erogelib.mapper.TtBlogTagPOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private TtBlogPOMapper ttBlogPOMapper;

    @Autowired
    private TtBlogTagPOMapper ttBlogTagPOMapper;

    @Override
    public List<BlogTagsDTO> queryBlogList() {
        LoginInfoDTO loginInfoDTO = ApplicationContextHelper.getBeanByType(LoginInfoDTO.class);
        Map<String, Object> params = new HashMap<>();
        params.put("userId" , loginInfoDTO.getUserId());
        return ttBlogPOMapper.queryBlogList(params);
    }

    @Override
    public List<UserTagsDTO> queryUserTags() {
        LoginInfoDTO loginInfoDTO = ApplicationContextHelper.getBeanByType(LoginInfoDTO.class);
        Map<String, Object> params = new HashMap<>();
        params.put("userId" , loginInfoDTO.getUserId());
        return ttBlogPOMapper.queryUserTags(params);
    }

    @Override
    public BlogTagsDTO saveBlog(BlogTagsDTO blogTagsDTO) {
        LoginInfoDTO loginInfoDTO = ApplicationContextHelper.getBeanByType(LoginInfoDTO.class);
        Date now = new Date();
        String userId = loginInfoDTO.getUserId();
        String userName = loginInfoDTO.getUserName();
        TtBlogPO ttBlogPO = new TtBlogPO();
        ttBlogPO.setBlogContent(blogTagsDTO.getBlogContent());
        ttBlogPO.setBlogTitle(blogTagsDTO.getBlogTitle());
        Long blogOldId = blogTagsDTO.getBlogId();
        if(blogTagsDTO.getBlogId() != null){
            LambdaQueryWrapper<TtBlogPO> query = new QueryWrapper<TtBlogPO>().lambda();
            query.eq(TtBlogPO::getBlogId , blogTagsDTO.getBlogId())
                    .eq(TtBlogPO::getBlogStatus, 11311001);
            List<TtBlogPO> ttBlogPOList = ttBlogPOMapper.selectList(query);
            if(ttBlogPOList == null || ttBlogPOList.isEmpty()){
                ttBlogPO.setUpdateBy(userId);
                ttBlogPO.setUpdateByName(userName);
                ttBlogPO.setUpdateDate(now);
                ttBlogPO.setCreateBy(userId);
                ttBlogPO.setCreateByName(userName);
                ttBlogPO.setCreateDate(now);
                ttBlogPO.setBlogStatus(11311001);
                ttBlogPOMapper.insert(ttBlogPO);
            } else {
                ttBlogPO.setUpdateBy(userId);
                ttBlogPO.setUpdateByName(userName);
                ttBlogPO.setUpdateDate(now);
                ttBlogPOMapper.update(ttBlogPO , query);
            }
        } else {
            ttBlogPO.setUpdateBy(userId);
            ttBlogPO.setUpdateByName(userName);
            ttBlogPO.setUpdateDate(now);
            ttBlogPO.setCreateBy(userId);
            ttBlogPO.setCreateByName(userName);
            ttBlogPO.setCreateDate(now);
            ttBlogPO.setBlogStatus(11311001);
            ttBlogPOMapper.insert(ttBlogPO);
        }
        blogTagsDTO.setBlogId(ttBlogPO.getBlogId());
        blogTagsDTO.setCreateBy(ttBlogPO.getCreateBy());
        blogTagsDTO.setCreateByName(ttBlogPO.getCreateByName());
        blogTagsDTO.setUpdateBy(ttBlogPO.getUpdateBy());
        blogTagsDTO.setUpdateByName(ttBlogPO.getUpdateByName());
        blogTagsDTO.setCreateDate(ttBlogPO.getCreateDate());
        blogTagsDTO.setUpdateDate(ttBlogPO.getUpdateDate());
        saveBlogTags(blogTagsDTO, blogOldId);
        return blogTagsDTO;
    }

    private void saveBlogTags(BlogTagsDTO blogTagsDTO, Long blogOldId) {
        Long blogId = blogTagsDTO.getBlogId();
        LoginInfoDTO loginInfoDTO = ApplicationContextHelper.getBeanByType(LoginInfoDTO.class);
        Date now = new Date();
        String userId = loginInfoDTO.getUserId();

        if(blogOldId != null){
            LambdaQueryWrapper<TtBlogTagPO> deleteTag = new QueryWrapper<TtBlogTagPO>().lambda();
            deleteTag.eq(TtBlogTagPO::getBlogId , blogOldId);
            ttBlogTagPOMapper.delete(deleteTag);
        }
        if(blogTagsDTO.getTagNames() != null && !blogTagsDTO.getTagNames().trim().equals("")){
            String [] blogTags = blogTagsDTO.getTagNames().trim().split(" ");
            for (String tag: blogTags){
                TtBlogTagPO ttBlogTagPO = new TtBlogTagPO();
                ttBlogTagPO.setBlogId(blogId);
                ttBlogTagPO.setTagName(tag);
                ttBlogTagPO.setCreateBy(userId);
                ttBlogTagPO.setCreateDate(now);
                ttBlogTagPOMapper.insert(ttBlogTagPO);
            }
        }
    }
}
