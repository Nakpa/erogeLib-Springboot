package com.library.eroge.erogelib.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.library.eroge.erogelib.entity.TmUserMd5PO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TmUserMd5Mapper extends BaseMapper<TmUserMd5PO> {
    int deleteByPrimaryKey(Integer indexId);

    TmUserMd5PO selectByPrimaryKey(Integer indexId);

    TmUserMd5PO selectByUserId(String userId);
}