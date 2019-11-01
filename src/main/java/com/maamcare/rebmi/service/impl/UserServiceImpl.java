package com.maamcare.rebmi.service.impl;

import com.maamcare.rebmi.annotation.Access;
import com.maamcare.rebmi.dao.UserMapper;
import com.maamcare.rebmi.exception.MyException;
import com.maamcare.rebmi.po.User;
import com.maamcare.rebmi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Integer register(User user) throws MyException{
        try{
            userMapper.addUser(user);
        }catch (DuplicateKeyException e){
            throw new MyException(-10,"用户已存在");
        }
        User userInfo = userMapper.getUserByUsername(user.getUsername());
        return userInfo.getId();
    }

    @Override
    public Integer login (String username, String password)throws MyException {
        User userInfo = userMapper.getUserByUsername(username);
        if(userInfo == null){
            throw new MyException(-8,"用户不存在");
        }
        if(!userInfo.getPassword().equals(password)){
            throw new MyException(-9,"密码错误");
        }
        return userInfo.getId();
    }



    @Override
    public User getUserInfoByUserId(Integer userId) throws MyException {
        User user = userMapper.getUserByUserId(userId);
        if(user == null){
            throw new MyException(-3,"用户不存在");
        }
        return user;
    }

    @Override
    public boolean updateHeight(Integer userId, Integer height) throws MyException {
        Integer col = userMapper.upDateHeight(userId,height);
        if(col==0){
            throw new MyException(-5,"用户不存在");
        }
        return true;
    }
}
