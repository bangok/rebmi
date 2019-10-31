package com.maamcare.rebmi.controller;



import com.maamcare.rebmi.exception.MyException;
import com.maamcare.rebmi.po.User;
import com.maamcare.rebmi.service.UserService;
import com.maamcare.rebmi.vo.ErrMap;
import com.maamcare.rebmi.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserConroller {

    @Autowired
    UserService userService;

    @GetMapping("/noLogin")
    public Result noLogin(HttpSession session){
        return Result.builder()
                .status(0)
                .err(new ErrMap(-1,"用户未登录"))
                .data(null)
                .build();
    }

    @PostMapping("/register")
    public Result register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam Integer height,
                           HttpSession session){
        //TODO
        Map<String,Integer> res= new HashMap<>();
        res.put("userId",1);

        return Result.builder()
                .status(1)
                .err(new ErrMap(0,""))
                .data(res)
                .build();

    }

    @PostMapping("/login")
    public Result login(@RequestParam String username,
                           @RequestParam String password
                           ){//HttpSession session
        //TODO
        Map<String,Integer> res= new HashMap<>();
        res.put("userid",1);

        return Result.builder()
                .status(1)
                .err(new ErrMap(0,""))
                .data(null)
                .build();

    }


    @GetMapping("/getUserInfoByUserId")
    @Transactional(rollbackFor = Exception.class)
    public Result getUserInfoByUserId(@RequestParam Integer userId) {
        ErrMap err = new ErrMap(0,"");

        User user=new User();
        if(userId==123){
            throw new MyException(-1,"123");
        }

        Map<String,String> res= new HashMap<>();
        res.put("username",user.getUsername());
        res.put("password",user.getPassword());
        return Result.builder()
                .status(1)
                .err(err)
                .data(res)
                .build();
    }

    @GetMapping("/updateHeight")
    public Result updateHeight(@RequestParam Integer userId,@RequestParam Integer hieght){

        return Result.builder()
                .status(1)
                .err(new ErrMap(0,""))
                .data(null)
                .build();
    }
}
