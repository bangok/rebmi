package com.maamcare.rebmi.controller;


import com.maamcare.rebmi.po.User;
import com.maamcare.rebmi.service.UserService;
import com.maamcare.rebmi.vo.ErrMap;
import com.maamcare.rebmi.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/user")
public class UserConroller {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public Result register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam Integer height,
                           HttpSession session){
        return Result.builder()
                .status(1)
                .err(new ErrMap(0,""))
                .data(null)
                .build();

    }

    @GetMapping("/getUserInfoById")
    public Result getUserInfoById(){
        User user = userService.getUserInfoByUserid(1);
        return Result.builder().status(1).err(new ErrMap(0,"")).data(user).build();
    }

    @GetMapping("/getUserInfoByUserid")
    public Result getUserInfoByUserid(){
        User user = userService.getUserInfoByUserid(1);
        return Result.builder().status(1).err(new ErrMap(0,"")).data(user).build();
    }
}
