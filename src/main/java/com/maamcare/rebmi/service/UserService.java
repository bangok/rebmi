package com.maamcare.rebmi.service;


import com.maamcare.rebmi.exception.MyException;
import com.maamcare.rebmi.po.User;

public interface UserService {
    public User getUserInfoByUserid(Integer userid) ;
    public Integer register(User user) throws MyException;
    public Integer login(String username,String password) throws MyException;
    public User getUserInfoByUserId(Integer userId) throws MyException;
    public boolean updateHeight(Integer userId,Integer height) throws MyException;
}
