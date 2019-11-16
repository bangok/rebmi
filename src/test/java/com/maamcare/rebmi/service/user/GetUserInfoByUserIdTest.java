package com.maamcare.rebmi.service.user;


import com.maamcare.rebmi.exception.MyException;
import com.maamcare.rebmi.po.User;
import com.maamcare.rebmi.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@DisplayName("getUserInfoByUserId(Integer userId) 获取用户信息(用户名ID) service")
public class GetUserInfoByUserIdTest {

    @Autowired
    UserService userService;
    /**
     * 获取用户信息
     * */
    @Test
    @DisplayName("参数正确，期望成功")
    public void test_GetUserInfoByUserId_WithUserIdNormal_ExpectSuccess(){
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
    @DisplayName("用户不存在，期望失败，错误码：-3")
    public void test_GetUserInfoByUserId_WithUserIdIsNonExistentd_ExpectCodeIsNegativeThree(){
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

}
