package com.maamcare.rebmi.service.user;


import com.maamcare.rebmi.exception.MyException;
import com.maamcare.rebmi.po.User;
import com.maamcare.rebmi.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegisterTest {

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
        Assertions.assertThat(e.getCode()).isEqualTo(-10);
    }

}
