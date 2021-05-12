package com.library.eroge.erogelib.service.tmuser;


import com.library.eroge.erogelib.dto.UserInfoDTO;
import com.library.eroge.erogelib.utils.TokenUtil;
import com.library.eroge.erogelib.utils.deModel.TokenMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserLoginAccessServiceImpl implements UserLoginAccessService {

    @Override
    public TokenMap userLogin(UserInfoDTO userInfoDTO) {
        String token = TokenUtil.sign(userInfoDTO);
        TokenMap tokenMap = TokenUtil.encrToken(token);
        tokenMap.setUserAcco(userInfoDTO.getUserAccount());
        log.info(" >>>>>>>>>>>>>> login successful >>>>>>>>>>>>>>>>> ");
        log.info(" >>>>>>>>>>>>>> loginAccount: {}" , userInfoDTO.getUserAccount());
        log.info(" >>>>>>>>>>>>>> token: {}" , token);
        return tokenMap;
    }
}
