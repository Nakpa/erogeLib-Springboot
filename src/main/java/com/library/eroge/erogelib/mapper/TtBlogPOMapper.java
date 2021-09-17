package com.library.eroge.erogelib.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.library.eroge.erogelib.dto.BlogTagsDTO;
import com.library.eroge.erogelib.dto.UserTagsDTO;
import com.library.eroge.erogelib.entity.TtBlogPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TtBlogPOMapper extends BaseMapper<TtBlogPO> {
    int deleteByPrimaryKey(Long blogId);

    TtBlogPO selectByPrimaryKey(Long blogId);

    List<BlogTagsDTO> queryBlogList(@Param("params") Map<String, Object> params);

    List<UserTagsDTO> queryUserTags(@Param("params") Map<String, Object> params);
}