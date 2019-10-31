package com.maamcare.rebmi.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class UserControllerTest {
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
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","zcf")
                .param("password","123456")
                .param("height","185")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(1));

    }

    @Test
    public void testRegisterWithUsernameIsNollExpectCodeIsNegativeOne() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","")
                .param("password","123456")
                .param("height","185")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-1));
    }

    @Test
    public void testRegisterWithUsernameLenghtThanEightExpectCodeIsNegativeTwo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","asdfghjkl")
                .param("password","123456")
                .param("height","185")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-2));
    }

    @Test
    public void testRegisterWithUsernameLenghtLessThanEightExpectCodeIsNegativeThree() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","a")
                .param("password","123456")
                .param("height","185")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-3));
    }

    @Test
    public void testRegisterWithUsernameHaveNotEnglistAndNumberCharExpectCodeIsNegativeFour() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","a@163d")
                .param("password","123456")
                .param("height","185")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-4));
    }

    @Test
    public void testRegisterWithPasswordIsNullExpectCodeIsNegativeFive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","a163d")
                .param("password","")
                .param("height","185")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-5));
    }

    @Test
    public void testRegisterWithPasswordLenghtIsNotSixExpectCodeIsNegativeSix() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","a163d")
                .param("password","dddddd3")
                .param("height","185")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-6));
    }

    @Test
    public void testRegisterWithPasswordHaveNotEnglishndNumberExpectCodeIsNegativeSeven() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","a163d")
                .param("password","ddddd@")
                .param("height","185")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-7));
    }

    @Test
    public void testRegisterWithHeightIsNullExpectCodeIsNegativeEight() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","a163d")
                .param("password","dddddd")
                .param("height","")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-8));
    }

    @Test
    public void testRegisterWithHeightIsNegExpectCodeIsNegativeNine() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/register")
                .param("username","a163d")
                .param("password","ddddd")
                .param("height","-185")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-9));
    }


    /**
     *用户登录
     * */

    @Test
    public void testLoginWithNormalExpectSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username","zcf")
                .param("password","123456")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(1));
    }

    @Test
    public void testLoginWithUsernameIsNullExpectCodeIsNegativeOne() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username","")
                .param("password","123456")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-1));
    }

    @Test
    public void testLoginWithUsernameIsMoreThanEightExpectCodeIsNegativeTwo() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username","ddddddddd")
                .param("password","123456")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-2));
    }

    @Test
    public void testLoginWithUsernameLenghtIsLessThanThreeExpectCodeIsNegativeThree() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username","d")
                .param("password","123456")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-3));
    }

    @Test
    public void testLoginWithUsernameNotIncludeEnglishAndNumberExpectCodeIsNegativeFour() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username","d@dd")
                .param("password","123456")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-4));
    }

    @Test
    public void testLoginWithPasswordIsNullExpectCodeIsNegativeFive() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username","ddd")
                .param("password","")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-5));
    }

    @Test
    public void testLoginWithPasswordLenghtIsNotSixExpectCodeIsNegativeSix() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username","zcf")
                .param("password","1234567")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-6));
    }

    @Test
    public void testLoginWithPasswordHaveIllegalCharacterExpectCodeIsNegativeSeven() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/user/login")
                .param("username","zcf")
                .param("password","12345@")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-7));
    }


    /**
     * 获取用户信息
     * */

    @Test
    public void testGetUserInfoByUserIdWithNormalExpectSuccess()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getUserInfoByUserId")
                .param("userId","1")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(1));
    }

    @Test
    public void testGetUserInfoByUserIdWithUserIdIsNullExpectCodeIsNegativeOne()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getUserInfoByUserId")
                .param("userId","")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-1));
    }

    @Test
    public void testGetUserInfoByUserIdWithUserIdIsNegExpectCodeIsNegativeTwo()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/getUserInfoByUserId")
                .param("userId","-3")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-2));
    }

    /**
     * 用户修改身高
     * */
    @Test
    public void testupdateHeightWithNormalExpectSuccess()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/updateHeight")
                .param("userId","1")
                .param("height","190")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.stauts").value(1));
    }

    @Test
    public void testupdateHeightWitUserIdIsNullExpectCodeIsNegativeOne()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/updateHeight")
                .param("userId","")
                .param("height","190")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-1));
    }

    @Test
    public void testupdateHeightWithUserIdIsNegExpectCodeIsNegativeTwo()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/updateHeight")
                .param("userId","-1")
                .param("height","190")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-2));
    }

    @Test
    public void testupdateHeightWithHeightIsNullExpectCodeIsNegativeThree()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/updateHeight")
                .param("userId","1")
                .param("height","")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-3));
    }

    @Test
    public void testupdateHeightWithHeightIsNegExpectCodeIsNegativeFour()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/updateHeight")
                .param("userId","1")
                .param("height","-3")
                .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.err.code").value(-4));
    }


}
