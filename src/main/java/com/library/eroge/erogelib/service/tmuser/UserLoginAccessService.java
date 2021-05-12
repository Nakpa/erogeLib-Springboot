package com.library.eroge.erogelib.service.tmuser;

import com.library.eroge.erogelib.dto.UserInfoDTO;
import com.library.eroge.erogelib.utils.deModel.TokenMap;

public interface UserLoginAccessService {

    TokenMap userLogin(UserInfoDTO userInfoDTO);

}
