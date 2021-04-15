package com.library.eroge.erogelib.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.library.eroge.erogelib.entity.TmUserPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface TmUserMapper extends BaseMapper<TmUserPO> {

    int deleteByPrimaryKey(String userId);

    TmUserPO selectByPrimaryKey(String userId);

    List<TmUserPO> queryUserList(@Param("params") TmUserPO params);

    TmUserPO selectPassword(@Param("userId") String userId);
}