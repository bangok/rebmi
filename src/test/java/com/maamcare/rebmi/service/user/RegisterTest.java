package com.maamcare.rebmi.service.user;


import com.maamcare.rebmi.exception.MyException;
import com.maamcare.rebmi.po.User;
import com.maamcare.rebmi.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;


@SpringBootTest
@DisplayName("register(User user) 用户注册(用户对象) service")
public class RegisterTest {

    @Autowired
    UserService userService;

    /**
     * 用户注册
     * */
    @Test
    @DisplayName("参数正确，期望成功")
    public void testRegisterWithNomalExpectSuccess(){
        //组装入参数据
        Random rm = new Random();
        User user = new User();
        int radomInt = rm.nextInt(1000)+1000;
        String s = String.valueOf(radomInt);
        user.setUsername("test"+s);
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
    @DisplayName("用户名已存在，期望失败，错误码：-10")
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
        Assertions.assertThat(e.getCode()).isEqualTo(-10);
    }

}
