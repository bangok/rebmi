package com.maamcare.rebmi.controller;


import com.maamcare.rebmi.po.User;
import com.maamcare.rebmi.service.UserService;
import com.maamcare.rebmi.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserConroller {

    @Autowired
    UserService userService;

    @GetMapping("/getUserInfoById")
    public Result getUserInfoById(){
        User user = userService.getUserInfoByUserid(1);
        return Result.builder().status(1).err(null).data(user).build();
    }
}
