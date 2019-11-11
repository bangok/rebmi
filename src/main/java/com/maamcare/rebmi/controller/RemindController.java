package com.maamcare.rebmi.controller;

import com.alibaba.fastjson.JSONObject;
import com.maamcare.rebmi.exception.MyException;
import com.maamcare.rebmi.po.WeightRecord;
import com.maamcare.rebmi.service.RemindService;
import com.maamcare.rebmi.vo.ErrMap;
import com.maamcare.rebmi.vo.common.Result;
import com.maamcare.rebmi.vo.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/remind")
public class RemindController {

    @Autowired
    RemindService remindService;

    @GetMapping("/getWeightByUserIdAndDate")
    public Result getWeightByUserIdAndDate(@RequestParam Integer userId,
                                           @RequestParam String anyDate) {
        StringUtil.parseStringToDate(anyDate);
        if (userId == null) {
            throw new MyException(-1, "用户Id不能为空");
        }
        if (userId < 0) {
            throw new MyException(-3, "用户Id不能为负");
        }
        if (anyDate.equals("")) {
            throw new MyException(-2, "查询日期不能为空");
        }

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(d);
        Integer i = anyDate.compareTo(str);
        if (i > 0) {
            throw new MyException(-4, "查询日期不能大于当前日期");
        }

        WeightRecord weightRecord = remindService.getWeightByUserIdAndDate(userId, anyDate);

        return Result.success(weightRecord);
    }
}
