package com.maamcare.rebmi.service;


import com.maamcare.rebmi.po.User;

public interface UserService {
    Integer register(User user);
    Integer login(String username,String password);
    User getUserInfoByUserId(Integer userId);
    boolean updateHeight(Integer userId,Integer height);
}
