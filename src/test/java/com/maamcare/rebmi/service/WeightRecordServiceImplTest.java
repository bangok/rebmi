package com.maamcare.rebmi.service;

import com.maamcare.rebmi.dto.MyDto;
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
        Integer userId = 99;
        String anyDate = "2019-10-13";
        User user;
        try {
            user = userService.getUserInfoByUserid(userId);
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
        }catch (MyException e){
            Assertions.assertThat(e.getCode()).isEqualTo(-10);
        }

    }


    @Test
    public void TestAddWeightRecordExpectSuccess() {
        Integer userId = 1;
        Integer weight = 600;
        String addDate = "2019-10-20";
        WeightRecord weightRecord;
        weightRecord =  weightRecordService.addWeightRecord(userId, weight, addDate);
        Assertions.assertThat(weightRecord.userId).isEqualTo(1);

    }

    @Test
    public void TestAddWeightRecordWithUserNothingnessExpectError() {
        Integer userId = 99;
        String anyDate = "2019-10-13";
        User user;
        try {
            user =  userService.getUserInfoByUserid(userId);
        }catch (MyException e){
            Assertions.assertThat(e.getCode()).isEqualTo(-4);
        }

    }

    @Test
    public void TestAddWeightRecordExpectError() {
        Integer userId = 1;
        Integer weight = 600;
        String addDate = "2019-10-20";
        WeightRecord weightRecord;
        try {
            weightRecord =  weightRecordService.addWeightRecord(userId, weight, addDate);
        }catch (MyException e){
            Assertions.assertThat(e.getCode()).isEqualTo(-7);
        }

    }


    @Test
    public void TestUpdateWeightRecordExpectSuccess() {
        Integer recordId = 1;
        Integer weight = 600;
        WeightRecord weightRecord = weightRecordService.updateWeightRecord(recordId, weight);
        Assertions.assertThat(weightRecord.getWeight()).isEqualTo(600);
    }

    @Test
    public void TestUpdateWeightRecordWithRecordNothingnessExpectError() {
        Integer recordId = 1000;
        Integer weight = 600;
        WeightRecord weightRecord;
        try {
            weightRecord= weightRecordService.updateWeightRecord(recordId, weight);
        }catch (MyException e){
            Assertions.assertThat(e.getCode()).isEqualTo(-3);
        }
    }

    @Test
    public void TestUpdateWeightRecordExpectError() {
        Integer recordId = 1000;
        Integer weight = 600;
        WeightRecord weightRecord;
        try {
            weightRecord= weightRecordService.updateWeightRecord(recordId, weight);
        }catch (MyException e){
          Assertions.assertThat(e.getCode()).isEqualTo(-6);

        }
    }
}
