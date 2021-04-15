package com.library.eroge.erogelib.controller.tmuser;

import com.library.eroge.erogelib.config.exceptionCatchUtils.annotation.ResponseResult;
import com.library.eroge.erogelib.dto.UserInfoDTO;
import com.library.eroge.erogelib.entity.TmUserPO;
import com.library.eroge.erogelib.service.tmuser.TmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@Transactional
@ResponseResult
@RequestMapping("/tmUser")
public class TmUserController {

    @Autowired
    private TmUserService tmUserService;

    // 查询用户
    @GetMapping("/queryTmUser")
    public List<TmUserPO> queryTmUser(TmUserPO tmUserPO) {
        List<TmUserPO> list = tmUserService.queryUserList(tmUserPO);
        return list;
    }

    // 新增账户
    @PostMapping("/insertTmUser")
    public void insertTmUser(@RequestBody @Valid TmUserPO tmUserPO) {
        tmUserService.insertTmUser(tmUserPO);
    }

    // 修改密码
    @PostMapping("/changePassword")
    public void changePassword(@RequestBody @Valid UserInfoDTO tmUserPO) {
        tmUserService.changePassword(tmUserPO);
    }
}
