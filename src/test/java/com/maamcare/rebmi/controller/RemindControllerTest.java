package com.maamcare.rebmi.controller;

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
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class RemindControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }
    //根据用户Id和时间查询某一天体重信息
    @Test
    public void TestGetWeightByUserIdAndDateExpectSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/getWeightByUserIdAndDate") //请求的url,请求的方法是get
                .param("userId","1")
                .param("anyDate","2019-10-23")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.data.userId").value(1))
                .andExpect(jsonPath("$.status").value(1));

    }
    @Test
    public void TestGetWeightByUserIdAndDateWithUserIdEmptyExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/getWeightByUserIdAndDate") //请求的url,请求的方法是get
                .param("userId",null)
                .param("anyDate","2019-10-23")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.code").value(-1));

    }
    @Test
    public void TestGetWeightByUserIdAndDateWithUserIdNegativeExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/getWeightByUserIdAndDate") //请求的url,请求的方法是get
                .param("userId","-1")
                .param("anyDate","2019-10-23")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.code").value(-3));

    }
    @Test
    public void TestGetWeightByUserIdAndDateWithAnyDateEmptyExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/getWeightByUserIdAndDate") //请求的url,请求的方法是get
                .param("userId","1")
                .param("anyDate","")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.code").value(-2));

    }
    @Test
    public void TestGetWeightByUserIdAndDateWithAnyDateGreaterThanCurrentDateExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/getWeightByUserIdAndDate") //请求的url,请求的方法是get
                .param("userId","1")
                .param("anyDate","2019-11-15")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.code").value(-4));

    }

}
