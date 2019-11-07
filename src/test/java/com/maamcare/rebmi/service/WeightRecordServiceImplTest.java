package com.maamcare.rebmi.service;


import com.maamcare.rebmi.exception.MyException;
import com.maamcare.rebmi.po.User;
import com.maamcare.rebmi.po.WeightRecord;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeightRecordServiceImplTest {
    @Autowired
    WeightRecordService weightRecordService;
    @Autowired
    UserService userService;

    @Test
    public void TestGetWeightListByTimeSoltAndUserIdExpectSuccess() {
        Integer userId = 1;
        String startDate = "2019-10-13";
        String endDate = "2019-10-20";
        List<WeightRecord> list = weightRecordService.GetWeightListByTimeSoltAndUserId(userId, startDate, endDate);
        Assertions.assertThat(list).isNotNull();
    }

    @Test
    public void TestGetWeightListByTimeSoltAndUserIdWithUserNothingnessExpectError() {
        Integer userId = 1;
        String startDate = "2019-10-13";
        String endDate = "2019-10-20";
        try {
            List<WeightRecord> list = weightRecordService.GetWeightListByTimeSoltAndUserId(userId, startDate, endDate);

            Assertions.fail("error");
        }catch (MyException e){
            Assertions.assertThat(e.getCode()).isEqualTo(-8);
        }

    }

    @Test
    public void TestGetWeightListByTimeSoltAndUserIdExpectError() {
        Integer userId = 1;
        String startDate = "2019-10-13";
        String endDate = "2019-10-20";
        List<WeightRecord> list;
        try {
            list =  weightRecordService.GetWeightListByTimeSoltAndUserId(userId, startDate, endDate);
            Assertions.fail("error");
        }catch (MyException e){
            Assertions.assertThat(e.getCode()).isEqualTo(-10);
        }

    }


    @Test
    public void TestAddWeightRecordExpectSuccess() {
        Integer userId = 1;
        Integer weight = 600;
        String addDate = "2019-10-20";
      boolean isSuccess = weightRecordService.addWeightRecord(userId, weight, addDate);
        Assertions.assertThat(isSuccess).isTrue();

    }

    @Test
    public void TestAddWeightRecordWithUserNothingnessExpectError() {
        Integer userId = 1;
        Integer weight = 600;
        String addDate = "2019-10-20";
        boolean isSuccess = true;
        try {
            isSuccess = weightRecordService.addWeightRecord(userId, weight, addDate);
            Assertions.fail("error");
        }catch (MyException e){
            Assertions.assertThat(e.getCode()).isEqualTo(-4);
        }

    }

    @Test
    public void TestAddWeightRecordExpectError() {
        Integer userId = 1;
        Integer weight = 600;
        String addDate = "2019-10-20";
        WeightRecord weightRecord = new WeightRecord();

        boolean isSuccess = true;
        try {
            isSuccess = weightRecordService.addWeightRecord(userId, weight, addDate);
            Assertions.fail("error");
        }catch (MyException e){
            Assertions.assertThat(e.getCode()).isEqualTo(-7);
        }

    }


    @Test
    public void TestUpdateWeightRecordByRecordIdExpectSuccess() {
        Integer recordId = 1;
        Integer weight = 600;

        boolean isSuccess = weightRecordService.updateWeightRecordByRecordId(recordId, weight);
        Assertions.assertThat(isSuccess).isTrue();
    }

    @Test
    public void TestUpdateWeightRecordByRecordIdWithRecordNothingnessExpectError() {
        Integer recordId = 1000;
        Integer weight = 600;
        boolean isSuccess =true;

        try {
        isSuccess = weightRecordService.updateWeightRecordByRecordId(recordId, weight);
            Assertions.fail("error");
        }catch (MyException e){
            Assertions.assertThat(e.getCode()).isEqualTo(-3);
        }
    }

    @Test
    public void TestUpdateWeightRecordByRecordIdExpectError() {
        Integer recordId = 1000;
        Integer weight = 600;
        boolean isSuccess =true;

        try {
            isSuccess = weightRecordService.updateWeightRecordByRecordId(recordId, weight);
            Assertions.fail("error");
        }catch (MyException e){
          Assertions.assertThat(e.getCode()).isEqualTo(-6);

        }
    }
}
