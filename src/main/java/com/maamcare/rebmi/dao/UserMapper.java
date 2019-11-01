package com.maamcare.rebmi.dao;

import com.maamcare.rebmi.po.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface UserMapper {
    public User getUserInfoByUserid(Integer userid);
    public boolean addUser(User user);
    public User getUserByUsername(String username);
}
