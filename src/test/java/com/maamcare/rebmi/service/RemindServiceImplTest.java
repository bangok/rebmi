package com.maamcare.rebmi.service;

import com.maamcare.rebmi.exception.MyException;
import com.maamcare.rebmi.po.WeightRecord;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RemindServiceImplTest {

    @Autowired
    RemindService remindService;
    @Autowired
    UserService userService;

    @Test
    @DisplayName("测试通过用户id和日期获取某一天体重信息参数正常期望成功")
    public void Test_GetWeightByUserIdAndDate_WithNormal_ExpectSuccess() {
        Integer userId = 1;
        String anyDate = "2019-10-13";
        WeightRecord weightRecord = remindService.getWeightByUserIdAndDate(userId, anyDate);
        Assertions.assertThat(weightRecord).isNotNull();
        Assertions.assertThat(weightRecord.getUserId()).isEqualTo(1);

    }

    @Test
    @DisplayName("测试通过用户id和日期获取某一天体重信息用户不存在期望失败")
    public void Test_GetWeightByUserIdAndDate_WithUserNothingness_ExpectError() {
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
    @DisplayName("测试通过用户id和日期获取某一天体重信息记录日期不存在期望成功")
    public void Test_GetWeightByUserIdAndDate_ExpectSuccess() {
        Integer userId = 1;
        String anyDate = "2019-11-13";
        WeightRecord weightRecord = remindService.getWeightByUserIdAndDate(userId, anyDate);
        Assertions.assertThat(weightRecord).isNull();

    }
}
