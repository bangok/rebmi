package com.maamcare.rebmi.controller;

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
public class RemindControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @BeforeEach
    public void setUp() throws Exception {
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }
    //根据用户Id和时间查询某一天体重信息
    @Test
    @DisplayName("测试通过用户Id和日期获取某一天体重信息参数正常期望成功")
    public void Test_GetWeightByUserIdAndDate_WithNormal_ExpectSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/remind/getWeightByUserIdAndDate") //请求的url,请求的方法是get
                .param("userId","1")
                .param("anyDate","2019-10-22")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(1))
                .andExpect(jsonPath("$.data.userId").value(1));


    }
    @Test
    @DisplayName("测试通过用户Id和日期获取某一天体重信息用户Id为空期望失败")
    public void Test_GetWeightByUserIdAndDate_WithUserIdEmpty_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/remind/getWeightByUserIdAndDate") //请求的url,请求的方法是get
                .param("userId","")
                .param("anyDate","2019-10-23")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-1));

    }

    @Test
    @DisplayName("测试通过用户Id和日期获取某一天体重信息用户Id为负期望失败")
    public void Test_GetWeightByUserIdAndDate_WithUserIdNegative_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/remind/getWeightByUserIdAndDate") //请求的url,请求的方法是get
                .param("userId","-1")
                .param("anyDate","2019-10-23")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-3));

    }
    @Test
    @DisplayName("测试通过用户Id和日期获取某一天体重信息查询日期为空期望失败")
    public void Test_GetWeightByUserIdAndDate_WithAnyDateEmpty_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/remind/getWeightByUserIdAndDate") //请求的url,请求的方法是get
                .param("userId","1")
                .param("anyDate","")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-2));

    }
    @Test
    @DisplayName("测试通过用户Id和日期获取某一天体重信息查询日期大于当前日期期望失败")
    public void Test_GetWeightByUserIdAndDate_WithAnyDateGreaterThanCurrentDate_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/remind/getWeightByUserIdAndDate") //请求的url,请求的方法是get
                .param("userId","1")
                .param("anyDate","2019-11-15")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-4));

    }
    @Test
    @DisplayName("测试通过用户Id和日期获取某一天体重信息查询日期不合法期望失败")
    public void Test_GetWeightByUserIdAndDate_WithAnyDateWrongFul_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/remind/getWeightByUserIdAndDate") //请求的url,请求的方法是get
                .param("userId","1")
                .param("anyDate","11-02-30")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-10));

    }
}
