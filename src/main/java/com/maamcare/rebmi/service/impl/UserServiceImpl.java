package com.maamcare.rebmi.service.impl;

import com.maamcare.rebmi.dao.UserMapper;
import com.maamcare.rebmi.dto.MyDto;
import com.maamcare.rebmi.exception.MyException;
import com.maamcare.rebmi.po.User;
import com.maamcare.rebmi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User getUserInfoByUserid(Integer userid) throws MyException{
        User user = userMapper.getUserInfoByUserid(userid);
        if(user==null){
            throw new MyException(-3,"用户不存在");
        }
        return user;
    }

    @Override
    public MyDto register(User user) {
        return null;
    }

    @Override
    public MyDto login(String username, String password) {
        return null;
    }

    @Override
    public MyDto getUserInfoByUserId(Integer userId) {
        return null;
    }

    @Override
    public MyDto updateHeight(Integer userId, Integer height) {
        return null;
    }
}
