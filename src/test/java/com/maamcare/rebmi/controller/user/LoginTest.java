package com.maamcare.rebmi.controller.user;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.Random;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class LoginTest {
    @Autowired
    private WebApplicationContext webApplicationContext;


    private MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }


    /**
     *用户登录
     * */

    @Test
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

}