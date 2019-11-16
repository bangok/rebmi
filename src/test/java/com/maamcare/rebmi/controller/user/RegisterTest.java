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

import java.util.Random;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@WebAppConfiguration
@DisplayName("register(String username,String password,Integer height) 用户登录(用户名，用户密码，身高) controller")
public class RegisterTest {
    @Autowired
    private WebApplicationContext webApplicationContext;


    private MockMvc mockMvc;
    @BeforeEach
    public void setUp() throws Exception {
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }


    /**
     * 用户注册
     * */

    @Test
    @DisplayName("参数正确，期望成功")
    public void test_Registe_rWithNormal_ExpectSuccess() throws Exception {
        Random rm = new Random();
        int radomInt = rm.nextInt(1000)+1000;
        String s = String.valueOf(radomInt);
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","test"+s)
                .param("password","123456")
                .param("height","185")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(1));

    }

    @Test
    @DisplayName("用户名为null，期望失败，错误码：-1")
    public void test_Register_WithUsernameIsNull_ExpectCodeIsNegativeOne() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("password","123456")
                .param("height","185")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-1));
    }

    @Test
    @DisplayName("用户名长度大于8，期望失败，错误码：-2")
    public void test_Register_WithUsernameLenghtThanEight_ExpectCodeIsNegativeTwo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","asdfghjkl")
                .param("password","123456")
                .param("height","185")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-2));
    }

    @Test
    @DisplayName("用户名长度小于3，期望失败，错误码：-3")
    public void test_Register_WithUsernameLenghtLessThanThree_ExpectCodeIsNegativeThree() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","a")
                .param("password","123456")
                .param("height","185")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-3));
    }

    @Test
    @DisplayName("用户名包含非法字符（非英文、数字组合），期望失败，错误码：-4")
    public void test_Register_WithUsernameIsHaveIllegalCharacter_ExpectCodeIsNegativeFour() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","a@163d")
                .param("password","123456")
                .param("height","185")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-4));
    }

    @Test
    @DisplayName("密码为null，期望失败，错误码：-5")
    public void test_Register_WithPasswordIsNull_ExpectCodeIsNegativeFive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","zcf")
                .param("height","185")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-5));
    }

    @Test
    @DisplayName("密码长度不等于6，期望失败，错误码：-6")
    public void test_Register_WithPasswordLenghtIsNotSix_ExpectCodeIsNegativeSix() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","zcf")
                .param("password","dddddd3")
                .param("height","185")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-6));
    }

    @Test
    @DisplayName("密码包含非法字符（非英文、数字组合），期望失败，错误码：-7")
    public void test_Registe_rWithPasswordHaveIllegalCharacter_ExpectCodeIsNegativeSeven() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","zcf")
                .param("password","ddddd@")
                .param("height","185")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-7));
    }

    @Test
    @DisplayName("身高为null，期望失败，错误码：-8")
    public void test_Register_WithHeightIsNull_ExpectCodeIsNegativeEight() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","zcf")
                .param("password","dddddd")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-8));
    }

    @Test
    @DisplayName("身高为负，期望失败，错误码：-9")
    public void test_Register_WithHeightIsNegative_ExpectCodeIsNegativeNine() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","zcf")
                .param("password","dddddd")
                .param("height","-185")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-9));
    }

    /**
     * service层的错误
     * */

    @Test
    @DisplayName("用户名已存在，期望失败，错误码：-10")
    public void test_Register_WithUsernameHaveExist_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","zcf")
                .param("password","123456")
                .param("height","185")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-10));
    }


}