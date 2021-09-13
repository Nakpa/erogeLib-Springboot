package com.library.eroge.erogelib.controller.group;


import com.library.eroge.erogelib.config.exceptionCatchUtils.annotation.ResponseResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@Transactional
@ResponseResult
@RequestMapping("/group")
public class GroupUserController {
}
