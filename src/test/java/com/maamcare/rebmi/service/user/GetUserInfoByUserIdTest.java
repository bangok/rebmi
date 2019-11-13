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

@RunWith(SpringRunner.class)
@SpringBootTest
public class GetUserInfoByUserIdTest {

    @Autowired
    UserService userService;
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
    public void testGetUserInfoByUserIdWithUserIdIsNonExistentdExpectCodeIsNegativeThree(){
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
