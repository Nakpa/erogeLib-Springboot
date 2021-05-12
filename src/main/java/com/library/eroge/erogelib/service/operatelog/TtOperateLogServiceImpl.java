package com.library.eroge.erogelib.service.operatelog;

import com.library.eroge.erogelib.entity.TtOperateLogPO;
import com.library.eroge.erogelib.mapper.TtOperateLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TtOperateLogServiceImpl implements TtOperateLogService {

    @Autowired
    private TtOperateLogMapper ttOperateLogMapper;

    @Override
    public void insertOperateLog(Integer operateType, String operateContent, String userId) {
        TtOperateLogPO ttOperateLogPO = new TtOperateLogPO();
        ttOperateLogPO.setOperateContent(operateContent);
        ttOperateLogPO.setOperateDate(new Date());
        ttOperateLogPO.setCreateDate(new Date());
        ttOperateLogPO.setOperator(userId);
        ttOperateLogPO.setOperateType(operateType);
        ttOperateLogMapper.insert(ttOperateLogPO);
    }
}
