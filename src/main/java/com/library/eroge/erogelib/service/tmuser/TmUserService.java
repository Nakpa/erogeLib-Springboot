package com.library.eroge.erogelib.service.tmuser;

import com.library.eroge.erogelib.dto.UserInfoDTO;
import com.library.eroge.erogelib.entity.TmUserPO;

import java.util.List;

public interface TmUserService {

    List<TmUserPO> queryUserList(TmUserPO tmUserPO);

    void insertTmUser(TmUserPO tmUserPO);

    void changePassword(UserInfoDTO tmUserPO);
}
