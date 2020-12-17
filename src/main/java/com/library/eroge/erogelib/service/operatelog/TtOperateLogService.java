package com.library.eroge.erogelib.service.operatelog;

public interface TtOperateLogService {

    void insertOperateLog(Integer operateType , String operateContent , String userId);
}
