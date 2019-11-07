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
public class WeightRecordControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Before
    public void setUp() throws Exception {
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }
    //获取一段时间体重记录
    @Test
    public void TestGetWeightListByTimeSoltAndUserIdExpectSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/GetWeightListByTimeSoltAndUserId") //请求的url,请求的方法是get
                .param("userId","1")
                .param("startDate","2019-10-22")
                .param("endDate","2019-10-23")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(1))
                .andExpect(jsonPath("$..data[0].userId").value(1));

    }
    @Test
    public void TestGetWeightListByTimeSoltAndUserIdWithUserIdEmptyExpectError() throws Exception {
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
    public void TestGetWeightListByTimeSoltAndUserIdWithUserIdNegativeExpectError() throws Exception {
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
    public void TestGetWeightListByTimeSoltAndUserIdWithStartDateEmptyExpectError() throws Exception {
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
    public void TestGetWeightListByTimeSoltAndUserIdWithEndDateEmptyExpectError() throws Exception {
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
    public void TestGetWeightListByTimeSoltAndUserIdWithStartDateGreaterThanEndDateExpectError() throws Exception {
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
    public void TestGetWeightListByTimeSoltAndUserIdWithEndDateGreaterThanCurrentDateExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/GetWeightListByTimeSoltAndUserId") //请求的url,请求的方法是get
                .param("userId","1")
                .param("startDate","2019-10-23")
                .param("endDate","2019-11-31")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-6));

    }
    @Test
    public void TestGetWeightListByTimeSoltAndUserIdWithStartDateGreaterThanCurrentDateExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/GetWeightListByTimeSoltAndUserId") //请求的url,请求的方法是get
                .param("userId","1")
                .param("startDate","2019-11-25")
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
    public void TestAddWeightRecordExpectSuccess() throws Exception {
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
    public void TestAddWeightRecordWithUserIdEmptyExpectError() throws Exception {
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
    public void TestAddWeightRecordWithUserIdNegativeExpectError() throws Exception {
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
    public void TestAddWeightRecordWithAddDateEmptyExpectError() throws Exception {
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
    public void TestAddWeightRecordWithAddDateGreaterThanCurrentDateExpectError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/weightRecord/addWeightRecord") //请求的url,请求的方法是get
                .param("userId","1")
                .param("weight","600")
                .param("addDate","2019-11-31")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-8));

    }
    @Test
    public void TestAddWeightRecordWithWeightEmptyExpectError() throws Exception {
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
    public void TestAddWeightRecordWithWeightNegativeExpectError() throws Exception {
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
    public void TestUpdateWeightRecordByRecordIdExpectSuccess() throws Exception {
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
    public void TestUpdateWeightRecordByRecordIdWithUserIdEmptyExpectError() throws Exception {
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
    public void TestUpdateWeightRecordByRecordIdWithRecordIdNegativeExpectError() throws Exception {
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
    public void TestUpdateWeightRecordByRecordIdWithWeightEmptyExpectError() throws Exception {
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
    public void TestUpdateWeightRecordByRecordIdWithWeightNegativeExpectError() throws Exception {
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
