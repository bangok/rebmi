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
public class RegisterTest {
    @Autowired
    private WebApplicationContext webApplicationContext;


    private MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }


    /**
     * 用户注册
     * */

    @Test
    public void testRegisterWithNormalExpectSuccess() throws Exception {
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
    public void testRegisterWithUsernameIsNullExpectCodeIsNegativeOne() throws Exception {
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
    public void testRegisterWithUsernameLenghtThanEightExpectCodeIsNegativeTwo() throws Exception {
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
    public void testRegisterWithUsernameLenghtLessThanThreeExpectCodeIsNegativeThree() throws Exception {
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
    public void testRegisterWithUsernameIsHaveIllegalCharacterExpectCodeIsNegativeFour() throws Exception {
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
    public void testRegisterWithPasswordIsNullExpectCodeIsNegativeFive() throws Exception {
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
    public void testRegisterWithPasswordLenghtIsNotSixExpectCodeIsNegativeSix() throws Exception {
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
    public void testRegisterWithPasswordHaveIllegalCharacterExpectCodeIsNegativeSeven() throws Exception {
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
    public void testRegisterWithHeightIsNullExpectCodeIsNegativeEight() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","zcf")
                .param("password","dddddd")
                .param("height","")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-8));
    }

    @Test
    public void testRegisterWithHeightIsNegativeExpectCodeIsNegativeNine() throws Exception {
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


}