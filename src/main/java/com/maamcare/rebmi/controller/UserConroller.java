package com.maamcare.rebmi.controller;



import com.maamcare.rebmi.exception.MyException;
import com.maamcare.rebmi.po.User;
import com.maamcare.rebmi.service.UserService;
import com.maamcare.rebmi.vo.ErrMap;
import com.maamcare.rebmi.vo.R;
import com.maamcare.rebmi.vo.Result;
import com.maamcare.rebmi.vo.UserRegisterInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
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
    public R register(@Validated UserRegisterInfoVo userRegisterInfoVo,
                           HttpSession session){
        User user = new User();
        user.setUsername(userRegisterInfoVo.getUsername());
        user.setPassword(userRegisterInfoVo.getPassword());
        user.setHeight(userRegisterInfoVo.getHeight());
        Integer userId =userService.register(user);
        session.setAttribute("loginUser",user);
        Map resData = new HashMap();
        resData.put("userId",userId);
        return R.success(resData);
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
    public Result getUserInfoByUserId(@RequestParam Integer userId){
        ErrMap err = new ErrMap(0,"");
        User user;
//        try {
            user = userService.getUserInfoByUserid(userId);
//        } catch (MyException e) {
//            err.setCode(e.getCode());
//            err.setMsg(e.getMsg());
//            return Result.builder()
//                    .status(0)
//                    .err(err)
//                    .data(null)
//                    .build();
//        }
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
