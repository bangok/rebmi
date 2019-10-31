package com.maamcare.rebmi.service;

import com.maamcare.rebmi.exception.MyException;
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
        //组装入参数据
        Random rm = new Random();
        User user = new User();
        int radomInt = rm.nextInt(1000)+1000;
        String s = String.valueOf(radomInt);
        user.setUsername("a"+s);
        user.setHeight(180);
        user.setPassword("123456");
        //准备异常和接收参数
        Integer userId = null;
        MyException e=new MyException();
        //执行单元函数
        try {
            userId = userService.register(user);
        } catch (MyException ex) {
            e =ex;
        }
        //断言
        Assertions.assertThat(userId).isNotNull();

    }

    @Test
    public void testRegisterWithUsernameRepeatExpectExpectFail(){
        User user = new User();
        user.setUsername("zcf");
        user.setHeight(180);
        user.setPassword("123456");
        MyException e=new MyException();
        try {
            userService.register(user);
        } catch (MyException ex) {
            e =ex;
        }
        Assertions.assertThat(1).isEqualTo(-10);
    }


    /**
     * 用户登录
     * */
    @Test
    public void testLoginWithUsernameAndPasswordNormalExpectSuccess(){
        String username = "zcf";
        String password = "123456";
        MyException e=new MyException();
        Integer userId=null;
        try {
            userId =userService.login(username,password);
        } catch (MyException ex) {
            e =ex;
        }
        Assertions.assertThat(userId).isNotNull();
    }

    @Test
    public void testLoginWithUsernameIsNotHaveAndPasswordNormalExpectCodeIsNegativeEight(){
        String username = "zcf999";
        String password = "123457";
        MyException e=new MyException();
        Integer userId=null;
        try {
            userId =userService.login(username,password);
        } catch (MyException ex) {
            e =ex;
        }
        Assertions.assertThat(e.getCode()).isEqualTo(-8);
    }

    @Test
    public void testLoginWithUsernameNormalAndPasswordErrorExpectCodeIsNegativeNine(){
        String username = "zcf";
        String password = "123457";
        MyException e=new MyException();
        Integer userId=null;
        try {
            userId =userService.login(username,password);
        } catch (MyException ex) {
            e =ex;
        }
        Assertions.assertThat(e.getCode()).isEqualTo(-9);
    }

    /**
     * 获取用户信息
     * */
    @Test
    public void testGetUserInfoByUserIdWithUserIdNormalExpectSuccess(){
        Integer userId = 1;
        MyException e=new MyException();
        User user = new User();
        try {
            user=userService.getUserInfoByUserId(userId);
        } catch (MyException ex) {
            e =ex;
        }
        Assertions.assertThat(user).isNotNull();
    }

    @Test
    public void testGetUserInfoByUserIdWithHaveNothUserIdExpectCodeIsNegativeThree(){
        Integer userId = 999;
        MyException e=new MyException();
        User user = new User();
        try {
            user=userService.getUserInfoByUserId(userId);
        } catch (MyException ex) {
            e =ex;
        }
        Assertions.assertThat(e.getCode()).isEqualTo(-3);
    }


    /**
     * 用户更新身高
     * */
    @Test
    public void testUpdateHeightWithNormalExpectSuccess(){
        Integer userId = 1;
        Integer height = 176;
        Assertions.assertThat(userService.updateHeight(userId,height)).isEqualTo(true);
    }

    @Test
    public void testUpdateHeightWithUserIdIsNotHaveExpectCodeIsNegativeFve(){
        Integer userId = 999;
        Integer height = 176;
        MyException e=new MyException();
        Boolean b = null;
        try {
            b=userService.updateHeight(userId,height);
        } catch (MyException ex) {
            e =ex;
        }
        Assertions.assertThat(e.getCode()).isEqualTo(-5);
    }


}
