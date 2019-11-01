package com.maamcare.rebmi.service;


import com.maamcare.rebmi.po.User;

public interface UserService {
    public Integer register(User user);
    public Integer login(String username,String password);
    public User getUserInfoByUserId(Integer userId);
    public boolean updateHeight(Integer userId,Integer height);
}
