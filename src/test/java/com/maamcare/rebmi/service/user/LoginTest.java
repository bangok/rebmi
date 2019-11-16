package com.maamcare.rebmi.service.user;


import com.maamcare.rebmi.exception.MyException;
import com.maamcare.rebmi.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("login(String username,String password) 用户登录(用户名，用户密码) service")
public class LoginTest {

    @Autowired
    UserService userService;


    /**
     * 用户登录
     * */
    @Test
    @DisplayName("参数正确，期望成功")
    public void test_Login_WithUsernameAndPasswordNormal_ExpectSuccess(){
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
    @DisplayName("用户不存在，期望失败，错误码：-8")
    public void test_Login_WithUsernameIsNotHaveAndPasswordNormal_ExpectCodeIsNegativeEight(){
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
    @DisplayName("密码错误，期望失败，错误码：-9")
    public void test_Login_WithUsernameNormalAndPasswordError_ExpectCodeIsNegativeNine(){
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
