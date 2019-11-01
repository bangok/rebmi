package com.maamcare.rebmi.dao;

import com.maamcare.rebmi.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface UserMapper {
    public User getUserByUserId(Integer userid);
    public boolean addUser(User user);
    public User getUserByUsername(String username);
    @Update("update user SET height=#{height} where id=#{userId}")
    public Integer upDateHeight(@Param(value = "userId") Integer userId,@Param(value = "height")Integer height);
}
