package com.maamcare.rebmi.dao;

import com.maamcare.rebmi.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface UserMapper {
    User getUserByUserId(Integer userid);
    boolean addUser(User user);
    User getUserByUsername(String username);
    @Update("UPDATE user SET height=#{height} WHERE id=#{userId}")
    Integer upDateHeight(@Param(value = "userId") Integer userId,@Param(value = "height")Integer height);
}
