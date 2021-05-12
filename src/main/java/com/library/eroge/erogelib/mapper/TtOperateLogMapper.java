package com.library.eroge.erogelib.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.library.eroge.erogelib.entity.TtOperateLogPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TtOperateLogMapper extends BaseMapper<TtOperateLogPO> {

    int deleteByPrimaryKey(Long logId);

    TtOperateLogPO selectByPrimaryKey(Long logId);
}