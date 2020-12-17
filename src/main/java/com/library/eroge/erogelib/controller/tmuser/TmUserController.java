package com.library.eroge.erogelib.controller.tmuser;

import com.library.eroge.erogelib.dto.UserInfoDTO;
import com.library.eroge.erogelib.entity.TmUserPO;
import com.library.eroge.erogelib.service.tmuser.TmUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Transactional
@RequestMapping("/tmUser")
public class TmUserController {

    @Autowired
    private TmUserService tmUserService;

    @GetMapping("/queryTmUser")
    public List<TmUserPO> queryTmUser(@RequestParam TmUserPO tmUserPO) {
        List<TmUserPO> list = tmUserService.queryUserList(tmUserPO);
        return list;
    }

    @PostMapping("/insertTmUser")
    public void insertTmUser(@RequestBody @Valid TmUserPO tmUserPO) {
        tmUserService.insertTmUser(tmUserPO);
    }

    @PostMapping("/changePassword")
    public void changePassword(@RequestBody @Valid UserInfoDTO tmUserPO) {
        tmUserService.changePassword(tmUserPO);
    }
}
