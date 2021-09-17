package com.library.eroge.erogelib.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.library.eroge.erogelib.entity.Md5PO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface Md5Mapper extends BaseMapper<Md5PO> {

    int deleteByPrimaryKey(Long indexId);

    Md5PO selectByPrimaryKey(Long indexId);

    List<Md5PO> selectByMd5(@Param("md5") String md5);

    Md5PO selectLastValidMd5();
}