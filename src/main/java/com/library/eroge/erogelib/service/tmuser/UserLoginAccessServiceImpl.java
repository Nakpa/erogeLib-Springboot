package com.library.eroge.erogelib.service.tmuser;


import com.library.eroge.erogelib.config.exceptionCatchUtils.BizExceptionHandler;
import com.library.eroge.erogelib.dto.UserInfoDTO;
import com.library.eroge.erogelib.entity.Md5PO;
import com.library.eroge.erogelib.entity.TmUserMd5PO;
import com.library.eroge.erogelib.entity.TmUserPO;
import com.library.eroge.erogelib.mapper.Md5Mapper;
import com.library.eroge.erogelib.mapper.TmUserMapper;
import com.library.eroge.erogelib.mapper.TmUserMd5Mapper;
import com.library.eroge.erogelib.utils.PasswordUtil;
import com.library.eroge.erogelib.utils.TokenUtil;
import com.library.eroge.erogelib.utils.deModel.TokenMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserLoginAccessServiceImpl implements UserLoginAccessService {

    @Autowired
    private Md5Mapper md5Mapper;

    @Autowired
    private TmUserMapper tmUserMapper;

    @Autowired
    private TmUserMd5Mapper tmUserMd5Mapper;

    @Override
    public TokenMap userLogin(UserInfoDTO userInfoDTO) {
        String password = userInfoDTO.getPassword();

        TmUserPO queryUser = new TmUserPO();
        queryUser.setUserAccount(userInfoDTO.getUserAccount());
        TmUserPO user = tmUserMapper.queryUserByAccount(queryUser);
        if(user == null){
            throw new BizExceptionHandler("没有找到这个账号的相关信息哦~");
        }
        // 校验密码
        String passwordEnc = user.getAccoPassword();
        TmUserMd5PO tmUserMd5PO = tmUserMd5Mapper.selectByUserId(user.getUserId());
        String passwordDec = decrypt(passwordEnc , tmUserMd5PO.getMd5());
        if(!password.equals(passwordDec)) {
            throw new BizExceptionHandler("密码错惹~");
        }

        String token = TokenUtil.sign(userInfoDTO);
        TokenMap tokenMap = TokenUtil.encrToken(token);
        tokenMap.setUserId(user.getUserId());
        tokenMap.setUserAcco(user.getUserAccount());
        tokenMap.setUserName(user.getUserName());
        tokenMap.setEmail(user.getEmail());
        tokenMap.setRemark(user.getRemark());
        log.info(" >>>>>>>>>>>>>> login successful >>>>>>>>>>>>>>>>> ");
        log.info(" >>>>>>>>>>>>>> loginAccount: {}" , userInfoDTO.getUserAccount());
        log.info(" >>>>>>>>>>>>>> token: {}" , token);
        return tokenMap;
    }



    // 加密
    private String encrypt(String password) {
        Md5PO md5PO = md5Mapper.selectLastValidMd5();
        String md5 = md5PO.getMd5();
        String passwordAfter = "";
        try {
            PasswordUtil passwordUtil = new PasswordUtil(md5);

            System.out.println("加密前的字符：" + password);

            passwordAfter = passwordUtil.encrypt(password);
            System.out.println("加密后的字符：" + passwordAfter);
//            System.out.println("解密后的字符：" + passwordUtil.decrypt(passwordAfter));

        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return passwordAfter;
    }

    // 解密
    private String decrypt(String oldPassword , String md5) {
//        Md5PO md5PO = md5Mapper.selectLastValidMd5();
//        String md5 = md5PO.getMd5();
        String passwordAfter = "";
        try {
            PasswordUtil passwordUtil = new PasswordUtil(md5);
            passwordAfter = passwordUtil.decrypt(oldPassword);
            System.out.println("解密后的字符：" + passwordAfter);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
        return passwordAfter;
    }
}
