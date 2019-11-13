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
@DisplayName("updateHeight(Integer userId,Integer height) 修改身高(用户ID，身高) controller")
public class UpdateHeightTest {
    @Autowired
    private WebApplicationContext webApplicationContext;


    private MockMvc mockMvc;
    @BeforeEach
    public void setUp() throws Exception {
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }


    /**
     * 用户修改身高
     * */
    @Test
    @DisplayName("参数正确，期望成功")
    public void testupdateHeightWithNormalExpectSuccess()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/updateHeight")
                .param("userId","1")
                .param("height","190")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(1));
    }

    @Test
    @DisplayName("用户ID为null，期望失败，错误码：-1")
    public void testupdateHeightWitUserIdIsNullExpectCodeIsNegativeOne()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/updateHeight")
                .param("height","190")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-1));
    }

    @Test
    @DisplayName("用户ID为负，期望失败，错误码：-2")
    public void testupdateHeightWithUserIdIsNegtiveExpectCodeIsNegativeTwo()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/updateHeight")
                .param("userId","-1")
                .param("height","190")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-2));
    }

    @Test
    @DisplayName("身高为null，期望失败，错误码：-3")
    public void testupdateHeightWithHeightIsNullExpectCodeIsNegativeThree()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/updateHeight")
                .param("userId","1")
                .param("height","")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-3));
    }

    @Test
    @DisplayName("身高为负，期望失败，错误码：-4")
    public void testupdateHeightWithHeightIsNegtiveExpectCodeIsNegativeFour()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/updateHeight")
                .param("userId","1")
                .param("height","-3")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-4));
    }

    @Test
    @DisplayName("用户ID不存在，期望失败，错误码：-5")
    public void testupdateHeightWithDataBaseHaveNotUserIdExpectCodeIsNegativeFive()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user/updateHeight")
                .param("userId","99999")
                .param("height","160")
                .contentType("application/json;charset=UTF-8") //数据的格式
                .accept("application/json;charset=UTF-8")
        ).andExpect(status().isOk())  //返回的状态是200
                .andDo(print()) //打印出请求和相应的内容
                .andExpect(jsonPath("$.status").value(0))
                .andExpect(jsonPath("$.err.code").value(-5));
    }


}
