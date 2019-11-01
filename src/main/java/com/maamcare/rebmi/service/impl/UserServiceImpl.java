package com.maamcare.rebmi.service.impl;

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
    public User getUserInfoByUserid(Integer userid) {
        User user = userMapper.getUserInfoByUserid(userid);
        if(user==null){
            throw new MyException(-3,"用户不存在");
        }
        return user;
    }

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
        return null;
    }

    @Override
    public User getUserInfoByUserId(Integer userId) throws MyException {
        return null;
    }

    @Override
    public boolean updateHeight(Integer userId, Integer height) throws MyException {
        return false;
    }
}
