package com.library.eroge.erogelib.controller.tmuser;


import com.library.eroge.erogelib.config.exceptionCatchUtils.annotation.ResponseResult;
import com.library.eroge.erogelib.dto.UserInfoDTO;
import com.library.eroge.erogelib.service.tmuser.UserLoginAccessService;
import com.library.eroge.erogelib.utils.deModel.TokenMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@Transactional
@ResponseResult
@RequestMapping("/access")
public class UserLoginAccess {

    @Autowired
    private UserLoginAccessService userLoginAccessService;

    // 用户登入
    @PostMapping("/userLogin")
    public TokenMap userLogin(@RequestBody @Valid UserInfoDTO userInfoDTO) {
        return userLoginAccessService.userLogin(userInfoDTO);
    }

    // 用户注册
    @PostMapping("/register")
    public void register(@RequestBody @Valid UserInfoDTO userInfoDTO) {
    }

    // 用户登出
    @PostMapping("/userLogout")
    public void userLogout(@RequestBody @Valid UserInfoDTO userInfoDTO) {
    }
}
