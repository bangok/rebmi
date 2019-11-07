package com.maamcare.rebmi.controller;



import com.maamcare.rebmi.annotation.Access;
import com.maamcare.rebmi.po.User;
import com.maamcare.rebmi.service.UserService;
import com.maamcare.rebmi.vo.ErrMap;
import com.maamcare.rebmi.vo.UserLoginInfoVo;
import com.maamcare.rebmi.vo.UserRegisterInfoVo;
import com.maamcare.rebmi.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping("/test")
    @Access(authorities = {"LOGIN"})
    public Result test(){
        return Result.success("权限控制");
    }


    @PostMapping("/register")
    public Result register(@Validated UserRegisterInfoVo userRegisterInfoVo,
                           HttpSession session){
        User user = new User();
        user.setUsername(userRegisterInfoVo.getUsername());
        user.setPassword(userRegisterInfoVo.getPassword());
        user.setHeight(userRegisterInfoVo.getHeight());
        Integer userId =userService.register(user);
        session.setAttribute("loginUser",user);
        Map resData = new HashMap();
        resData.put("userId",userId);
        return Result.success(resData);
    }

    @GetMapping("/login")
    public Result login(@Validated UserLoginInfoVo userLoginInfoVo,
                        HttpSession session
                           ){//HttpSession session

        Integer userId = userService.login(userLoginInfoVo.username,userLoginInfoVo.password);
        session.setAttribute("loginUser",userLoginInfoVo.username);
        Map resData = new HashMap();
        resData.put("userId",userId);
        return Result.success(resData);

    }



    @GetMapping("/getUserInfoByUserId")
    public Result getUserInfoByUserId(Integer userId){
        ErrMap err = new ErrMap(0,"");
        if(userId==null){
            err.setCode(-1);
            err.setMsg("用户id为空");
            return Result.fail(err);
        }
        if(userId<0){
            err.setCode(-2);
            err.setMsg("用户id为负");
            return Result.fail(err);
        }

        User user = userService.getUserInfoByUserId(userId);
        Map resData = new HashMap();
        resData.put("username",user.getUsername());
        resData.put("height",user.getHeight());
        return Result.success(resData);
    }


    @GetMapping("/updateHeight")
    public Result updateHeight(Integer userId,Integer height){
        ErrMap err = new ErrMap(0,"");
        if(userId==null){
            err.setCode(-1);
            err.setMsg("用户id为空");
            return Result.fail(err);
        }
        if(userId<0){
            err.setCode(-2);
            err.setMsg("用户id为负");
            return Result.fail(err);
        }
        if(height==null){
            err.setCode(-3);
            err.setMsg("用户身高为空");
            return Result.fail(err);
        }
        if(height<0){
            err.setCode(-4);
            err.setMsg("用户身高为负");
            return Result.fail(err);
        }
        userService.updateHeight(userId,height);
        return Result.success(null);
    }
}
