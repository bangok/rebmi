package com.maamcare.rebmi.service;


import com.maamcare.rebmi.exception.MyException;
import com.maamcare.rebmi.po.WeightRecord;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class WeightRecordServiceImplTest {
    @Autowired
    WeightRecordService weightRecordService;
    @Autowired
    UserService userService;

    @Test
    @DisplayName("测试通过用户id和一段时间获取体重记录列表参数正常期望成功")
    public void Test_GetWeightListByTimeSoltAndUserId_WithNormal_ExpectSuccess() {
        Integer userId = 1;
        String startDate = "2019-10-13";
        String endDate = "2019-10-20";
        List<WeightRecord> list = weightRecordService.GetWeightListByTimeSoltAndUserId(userId, startDate, endDate);
        Assertions.assertThat(list).isNotNull();
    }

    @Test
    @DisplayName("测试通过用户id和一段时间获取体重记录列表用户不存在期望失败")
    public void Test_GetWeightListByTimeSoltAndUserId_WithUserNothingness_ExpectError() {
        Integer userId = 99;
        String startDate = "2019-10-13";
        String endDate = "2019-10-20";
        try {
            List<WeightRecord> list = weightRecordService.GetWeightListByTimeSoltAndUserId(userId, startDate, endDate);

            Assertions.fail("error");
        }catch (MyException e){
            Assertions.assertThat(e.getCode()).isEqualTo(-5);
        }

    }


    @Test
    @DisplayName("测试添加体重记录参数正常期望成功")
    public void Test_AddWeightRecord_WithNormal_ExpectSuccess() {
        Integer userId = 1;
        Integer weight = 600;
        String addDate = "2019-10-20";
      boolean isSuccess = weightRecordService.addWeightRecord(userId, weight, addDate);
        Assertions.assertThat(isSuccess).isTrue();

    }

    @Test
    @DisplayName("测试添加体重记录用户不存在期望失败")
    public void Test_AddWeightRecord_WithUserNothingness_ExpectError() {
        Integer userId = 99;
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

//    @Test
//    @DisplayName("测试添加体重记录期望失败")
//    public void Test_AddWeightRecord_ExpectError() {
//        Integer userId = 1;
//        Integer weight = 600;
//        String addDate = "2019-10-17";
//        WeightRecord weightRecord = new WeightRecord();
//
//        boolean isSuccess = true;
//        try {
//            isSuccess = weightRecordService.addWeightRecord(userId, weight, addDate);
//            Assertions.fail("error");
//        }catch (MyException e){
//            Assertions.assertThat(e.getCode()).isEqualTo(-7);
//        }
//
//    }

    @Test
    @DisplayName("测试修改体重记录参数正常期望成功")
    public void Test_UpdateWeightRecordByRecordId_WithNormal_ExpectSuccess() {
        Integer recordId = 1;
        Integer weight = 600;

        boolean isSuccess = weightRecordService.updateWeightRecordByRecordId(recordId, weight);
        Assertions.assertThat(isSuccess).isTrue();
    }

    @Test
    @DisplayName("测试修改体重记录记录不存在期望失败")
    public void Test_UpdateWeightRecordByRecordId_WithRecordNothingness_ExpectError() {
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

//    @Test
//    @DisplayName("测试修改体重记录期望失败")
//    public void Test_UpdateWeightRecordByRecordId_ExpectError() {
//        Integer recordId = 1;
//        Integer weight = 600;
//        boolean isSuccess =true;
//
//        try {
//            isSuccess = weightRecordService.updateWeightRecordByRecordId(recordId, weight);
//            Assertions.fail("error");
//        }catch (MyException e){
//          Assertions.assertThat(e.getCode()).isEqualTo(-6);
//
//        }
//    }
}
