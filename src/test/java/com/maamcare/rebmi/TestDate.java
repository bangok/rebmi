package com.maamcare.rebmi;

import com.maamcare.rebmi.service.impl.DateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
public class TestDate {

    @Autowired
    DateService dateService;


    /**
     * 测试时间区
     * */
    @Test
    public void test(){
        dateService.addDate();
    }
}
