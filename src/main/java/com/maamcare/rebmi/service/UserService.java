package com.maamcare.rebmi.service;

import com.maamcare.rebmi.dto.MyDto;
import com.maamcare.rebmi.exception.MyException;
import com.maamcare.rebmi.po.User;

public interface UserService {
    public User getUserInfoByUserid(Integer userid) throws MyException;
    public MyDto register(User user);
    public MyDto login(String username,String password);
    public MyDto getUserInfoByUserId(Integer userId);
    public MyDto updateHeight(Integer userId,Integer height);
}
