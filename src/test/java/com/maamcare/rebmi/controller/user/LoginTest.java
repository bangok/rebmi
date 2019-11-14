package com.maamcare.rebmi.controller.user;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@WebAppConfiguration
@DisplayName("login(String username,String password) 用户登录(用户名，用户密码) controller")
public class LoginTest {
    @Autowired
    private WebApplicationContext webApplicationContext;


    private MockMvc mockMvc;
    @BeforeEach
    public void setUp() throws Exception {
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }


    /**
     *用户登录
     * */

    @Test
    @DisplayName("参数合法(username:zcf,password:123456)，期望成功")
    public void testLoginWithNormalExpectSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username","zcf")
                .param("password","123456")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(1));
    }

    @Test
    @DisplayName("用户名为null，期望失败，错误码：-1")
    public void testLoginWithUsernameIsNullExpectCodeIsNegativeOne() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("password","123456")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-1));
    }

    @Test
    @DisplayName("用户名为长度大于8，期望失败，错误码：-2")
    public void testLoginWithUsernameIsMoreThanEightExpectCodeIsNegativeTwo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username","ddddddddd")
                .param("password","123456")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-2));
    }

    @Test
    @DisplayName("用户名为长度小于3，期望失败，错误码：-3")
    public void testLoginWithUsernameLenghtIsLessThanThreeExpectCodeIsNegativeThree() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username","d")
                .param("password","123456")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-3));
    }

    @Test
    @DisplayName("用户名包含非法字符（非英文、数字组合），期望失败，错误码：-4")
    public void testLoginWithUsernameHaveIllegalCharacterExpectCodeIsNegativeFour() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username","d@dd")
                .param("password","123456")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-4));
    }

    @Test
    @DisplayName("密码为null，期望失败，错误码：-5")
    public void testLoginWithPasswordIsNullExpectCodeIsNegativeFive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username","ddd")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-5));
    }

    @Test
    @DisplayName("密码长度不等于6，期望失败，错误码：-6")
    public void testLoginWithPasswordLenghtIsNotSixExpectCodeIsNegativeSix() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username","zcf")
                .param("password","1234567")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-6));
    }

    @Test
    @DisplayName("密码包含非法字符（非英文、数字组合），期望失败，错误码：-7")
    public void testLoginWithPasswordHaveIllegalCharacterExpectCodeIsNegativeSeven() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username","zcf")
                .param("password","12345@")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-7));
    }

    /**
     * service层的错误
     * */
    @Test
    @DisplayName("用户名不存在，期望失败，错误码：-8")
    public void testLoginWithUsernameNotHaveExpectFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username","zcf999")
                .param("password","123456")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-8));
    }

    @Test
    @DisplayName("密码错误，期望失败，错误码：-9")
    public void testLoginWithPasswordErrorExpectFail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username","zcf")
                .param("password","12345d")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-9));
    }

}