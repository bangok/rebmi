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
public class UpdateHeightTest {

    @Autowired
    UserService userService;
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
    public void testUpdateHeightWithUserIdIsNonExistentdExpectCodeIsNegativeFve(){
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
