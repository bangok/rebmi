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
@DisplayName("updateHeight(Integer userId,Integer height) 修改身高(用户ID，身高)")
public class UpdateHeightTest {

    @Autowired
    UserService userService;
    /**
     * 用户更新身高
     * */
    @Test
    @DisplayName("参数正确，期望成功")
    public void testUpdateHeightWithNormalExpectSuccess(){
        Integer userId = 1;
        Integer height = 176;
        Assertions.assertThat(userService.updateHeight(userId,height)).isEqualTo(true);
    }

    @Test
    @DisplayName("用户不存在，期望失败，错误码：-5")
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
