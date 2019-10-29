package com.maamcare.rebmi.service;

import com.maamcare.rebmi.po.User;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    public  void  getUserInfoByIdWithUserIdNomalExpectSuccess(){
         Integer userId = 1;
        User user= new User() ;
      //  user = userService.getUserInfoByUserid(userId);
//        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getUsername()).isEqualTo("zcf");
    }
}
