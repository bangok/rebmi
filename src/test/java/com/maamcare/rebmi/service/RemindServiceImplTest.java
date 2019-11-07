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

@RunWith(SpringRunner.class)
@SpringBootTest
public class RemindServiceImplTest {

    @Autowired
    RemindService remindService;
    @Autowired
    UserService userService;

    @Test
    public void TestGetWeightByUserIdAndDateExpectSuccess() {
        Integer userId = 1;
        String anyDate = "2019-10-13";
        WeightRecord weightRecord = remindService.getWeightByUserIdAndDate(userId, anyDate);
        Assertions.assertThat(weightRecord).isNotNull();
        Assertions.assertThat(weightRecord.getUserId()).isEqualTo(1);

    }

    @Test
    public void TestGetWeightByUserIdAndDateWithUserNothingnessExpectError() {
        Integer userId = 111;
        String anyDate = "2019-10-13";
        try {
            WeightRecord weightRecord = remindService.getWeightByUserIdAndDate(userId, anyDate);
            Assertions.fail("error");
        } catch (MyException ex) {

            Assertions.assertThat(ex.getCode()).isEqualTo(-5);

        }

    }

    @Test
    public void TestGetWeightByUserIdAndDateExpectError() {
        Integer userId = 1;
        String anyDate = "2019-11-13";
        WeightRecord weightRecord;
        try {
            weightRecord = remindService.getWeightByUserIdAndDate(userId, anyDate);
            Assertions.fail("error");
        } catch (MyException e) {
            Assertions.assertThat(e.getCode()).isEqualTo(-6);

        }


    }
}
