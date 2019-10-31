package com.maamcare.rebmi.service;

import com.maamcare.rebmi.po.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    UserService userService;


    /**
     * 用户注册
     * */
    @Test
    public void testRegisterWithNomalExpectSuccess(){
        Random rm = new Random();
        User user = new User();
        int radomInt = rm.nextInt(1000)+1000;
        String s = String.valueOf(radomInt);
        user.setUsername("a"+s);
        user.setHeight(180);
        user.setPassword("123456");

        Exception e;
        try {
            userService.register(user);
        } catch (Exception ex) {
        }
        Assertions.assertThat(userService.register(user).getCode()).isEqualTo(0);
    }

    @Test
    public void testRegisterWithUsernameRepeatExpectExpectCodeIsNegativeTen(){
        User user = new User();
        user.setUsername("zcf");
        user.setHeight(180);
        user.setPassword("123456");
        Assertions.assertThat(userService.register(user).getCode()).isEqualTo(-10);
    }


    /**
     * 用户登录
     * */
    @Test
    public void testLoginWithUsernameAndPasswordNormalExpectSuccess(){
        String username = "zcf";
        String password = "123456";
        Assertions.assertThat(userService.login(username,password).getCode()).isEqualTo(0);
    }

    @Test
    public void testLoginWithUsernameIsNotHaveAndPasswordNormalExpectCodeIsNegativeEight(){
        String username = "zcf999";
        String password = "123457";
        Assertions.assertThat(userService.login(username,password).getCode()).isEqualTo(-8);
    }

    @Test
    public void testLoginWithUsernameNormalAndPasswordErrorExpectCodeIsNegativeNine(){
        String username = "zcf";
        String password = "123457";
        Assertions.assertThat(userService.login(username,password).getCode()).isEqualTo(-9);
    }

    /**
     * 获取用户信息
     * */
    @Test
    public void testGetUserInfoByUserIdWithUserIdNormalExpectSuccess(){
        Integer userId = 1;
        Assertions.assertThat(userService.getUserInfoByUserId(userId).getCode()).isEqualTo(0);
    }

    @Test
    public void testGetUserInfoByUserIdWithHaveNothUserIdExpectCodeIsNegativeThree(){
        Integer userId = 999;
        Assertions.assertThat(userService.getUserInfoByUserId(userId).getCode()).isEqualTo(-3);
    }


    /**
     * 用户更新身高
     * */
    @Test
    public void testUpdateHeightWithNormalExpectSuccess(){
        Integer userId = 1;
        Integer height = 176;
        Assertions.assertThat(userService.updateHeight(userId,height)).isEqualTo(0);
    }

    @Test
    public void testUpdateHeightWithUserIdIsNotHaveExpectCodeIsNegativeFve(){
        Integer userId = 999;
        Integer height = 176;
        Assertions.assertThat(userService.updateHeight(userId,height)).isEqualTo(-5);
    }


}
