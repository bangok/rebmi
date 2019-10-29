package com.maamcare.rebmi.service.impl;

import com.maamcare.rebmi.dao.UserMapper;
import com.maamcare.rebmi.dto.MyDto;
import com.maamcare.rebmi.po.User;
import com.maamcare.rebmi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public MyDto getUserInfoByUserid(Integer userid) {
        User user = userMapper.getUserInfoByUserid(userid);
        return MyDto.builder()
                .code(0)
                .msg("")
                .data(user)
                .build();
    }
}
