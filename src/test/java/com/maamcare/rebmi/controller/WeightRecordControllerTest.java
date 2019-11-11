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
public class WeightRecordControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @BeforeEach
    public void setUp() throws Exception {
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }
    //获取一段时间体重记录
    @Test
    @DisplayName("测试获取一段时间体重记录参数正常期望成功")
    public void Test_GetWeightListByTimeSoltAndUserId_withNormal_ExpectSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/GetWeightListByTimeSoltAndUserId") //请求的url,请求的方法是get
                .param("userId","2")
                .param("startDate","2019-10-22")
                .param("endDate","2019-10-23")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(1))
                .andExpect(jsonPath("$.data").exists());
//                .andExpect(jsonPath("$..data[0].userId").value(2));

    }
    @Test
    @DisplayName("测试获取一段时间体重记录userId为空期望失败")
    public void Test_GetWeightListByTimeSoltAndUserId_WithUserIdEmpty_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/GetWeightListByTimeSoltAndUserId") //请求的url,请求的方法是get
                .param("userId","")
                .param("startDate","2019-10-22")
                .param("endDate","2019-10-23")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-1));

    }
    @Test
    @DisplayName("测试获取一段时间体重记录userId为负期望失败")
    public void Test_GetWeightListByTimeSoltAndUserId_WithUserIdNegative_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/GetWeightListByTimeSoltAndUserId") //请求的url,请求的方法是get
                .param("userId","-1")
                .param("startDate","2019-10-22")
                .param("endDate","2019-10-23")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-9));

    }
    @Test
    @DisplayName("测试获取一段时间体重记录开始日期为空期望失败")
    public void Test_GetWeightListByTimeSoltAndUserId_WithStartDateEmpty_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/GetWeightListByTimeSoltAndUserId") //请求的url,请求的方法是get
                .param("userId","1")
                .param("startDate","")
                .param("endDate","2019-10-23")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-2));

    }
    @Test
    @DisplayName("测试获取一段时间体重记录开始日期不合法期望失败")
    public void Test_GetWeightListByTimeSoltAndUserId_WithStartDateWrongFul_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/GetWeightListByTimeSoltAndUserId") //请求的url,请求的方法是get
                .param("userId","1")
                .param("startDate","aaa")
                .param("endDate","2019-10-23")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-10));

    }
    @Test
    @DisplayName("测试获取一段时间体重记录结束日期不合法期望失败")
    public void Test_GetWeightListByTimeSoltAndUserId_WithEndDateWrongFul_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/GetWeightListByTimeSoltAndUserId") //请求的url,请求的方法是get
                .param("userId","1")
                .param("startDate","2019-10-23")
                .param("endDate","ddd")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-10));

    }
    @Test
    @DisplayName("测试获取一段时间体重记录结束日期为空期望失败")
    public void Test_GetWeightListByTimeSoltAndUserId_WithEndDateEmpty_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/GetWeightListByTimeSoltAndUserId") //请求的url,请求的方法是get
                .param("userId","1")
                .param("startDate","2019-10-22")
                .param("endDate","")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-3));

    }

    @Test
    @DisplayName("测试获取一段时间体重记录开始日期大于结束日期期望失败")
    public void Test_GetWeightListByTimeSoltAndUserId_WithStartDateGreaterThanEndDate_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/GetWeightListByTimeSoltAndUserId") //请求的url,请求的方法是get
                .param("userId","1")
                .param("startDate","2019-10-23")
                .param("endDate","2019-10-22")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-4));

    }
    @Test
    @DisplayName("测试获取一段时间体重记录结束日期大于当前日期期望失败")
    public void Test_GetWeightListByTimeSoltAndUserId_WithEndDateGreaterThanCurrentDate_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/GetWeightListByTimeSoltAndUserId") //请求的url,请求的方法是get
                .param("userId","1")
                .param("startDate","2019-10-23")
                .param("endDate","2019-11-30")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-6));

    }
    @Test
    @DisplayName("测试获取一段时间体重记录开始日期大于当前日期期望失败")
    public void Test_GetWeightListByTimeSoltAndUserId_WithStartDateGreaterThanCurrentDate_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/GetWeightListByTimeSoltAndUserId") //请求的url,请求的方法是get
                .param("userId","1")
                .param("startDate","2019-11-12")
                .param("endDate","2019-11-28")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-7));

    }
//添加体重记录
    @Test
    @DisplayName("测试添加体重记录参数正常期望成功")
    public void Test_AddWeightRecord_WithNormal_ExpectSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/addWeightRecord") //请求的url,请求的方法是get
                .param("userId","1")
                .param("weight","600")
                .param("addDate","2019-10-23")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(1));

    }
    @Test
    @DisplayName("测试添加体重记录userId为空期望失败")
    public void Test_AddWeightRecord_WithUserIdEmpty_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/addWeightRecord") //请求的url,请求的方法是get
                .param("userId","")
                .param("weight","600")
                .param("addDate","2019-10-23")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-1));

    }
    @Test
    @DisplayName("测试添加体重记录userId为负期望失败")
    public void Test_AddWeightRecord_WithUserIdNegative_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/addWeightRecord") //请求的url,请求的方法是get
                .param("userId","-1")
                .param("weight","600")
                .param("addDate","2019-10-23")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-6));

    }
    @Test
    @DisplayName("测试添加体重记录添加日期为空期望失败")
    public void Test_AddWeightRecord_WithAddDateEmpty_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/addWeightRecord") //请求的url,请求的方法是get
                .param("userId","1")
                .param("weight","600")
                .param("addDate","")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-2));

    }
    @Test
    @DisplayName("测试添加体重记录添加日期不合法期望失败")
    public void Test_AddWeightRecord_WithAddDateWrongFul_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/addWeightRecord") //请求的url,请求的方法是get
                .param("userId","1")
                .param("weight","600")
                .param("addDate","fff")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-10));

    }
    @Test
    @DisplayName("测试添加体重记录添加日期大于当前日期期望失败")
    public void Test_AddWeightRecord_WithAddDateGreaterThanCurrentDate_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/addWeightRecord") //请求的url,请求的方法是get
                .param("userId","1")
                .param("weight","600")
                .param("addDate","2019-11-30")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-8));

    }
    @Test
    @DisplayName("测试添加体重记录体重为空期望失败")
    public void Test_AddWeightRecord_WithWeightEmpty_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/addWeightRecord") //请求的url,请求的方法是get
                .param("userId","1")
                .param("weight","")
                .param("addDate","2019-10-30")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-3));

    }
    @Test
    @DisplayName("测试添加体重记录体重为负期望失败")
    public void Test_AddWeightRecord_WithWeightNegative_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/addWeightRecord") //请求的url,请求的方法是get
                .param("userId","1")
                .param("weight","-60")
                .param("addDate","2019-10-30")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-5));

    }
    //修改体重记录
    @Test
    @DisplayName("测试修改体重记录参数正常期望成功")
    public void Test_UpdateWeightRecordByRecordId_WithNormal_ExpectSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/updateWeightRecordByRecordId") //请求的url,请求的方法是get
                .param("recordId","1")
                .param("weight","600")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(1));

    }
    @Test
    @DisplayName("测试修改体重记录recordId为空期望失败")
    public void Test_UpdateWeightRecordByRecordId_WithRecordIdIdEmpty_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/updateWeightRecordByRecordId") //请求的url,请求的方法是get
                .param("recordId","")
                .param("weight","600")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-1));

    }
    @Test
    @DisplayName("测试修改体重记录recordId为负期望失败")
    public void Test_UpdateWeightRecordByRecordId_WithRecordIdNegative_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/updateWeightRecordByRecordId") //请求的url,请求的方法是get
                .param("recordId","-1")
                .param("weight","600")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-5));

    }
    @Test
    @DisplayName("测试修改体重记录体重为空期望失败")
    public void Test_UpdateWeightRecordByRecordId_WithWeightEmpty_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/updateWeightRecordByRecordId") //请求的url,请求的方法是get
                .param("recordId","1")
                .param("weight","")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-2));

    }
    @Test
    @DisplayName("测试修改体重记录体重为负期望失败")
    public void Test_UpdateWeightRecordByRecordId_WithWeightNegative_ExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/updateWeightRecordByRecordId") //请求的url,请求的方法是get
                .param("recordId","1")
                .param("weight","-600")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-4));

    }
}
