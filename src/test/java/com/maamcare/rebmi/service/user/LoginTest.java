package com.maamcare.rebmi.service.user;


import com.maamcare.rebmi.exception.MyException;
import com.maamcare.rebmi.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginTest {

    @Autowired
    UserService userService;


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

}
