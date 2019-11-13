package com.maamcare.rebmi.controller;

import com.maamcare.rebmi.exception.MyException;
import com.maamcare.rebmi.po.WeightRecord;
import com.maamcare.rebmi.service.WeightRecordService;
import com.maamcare.rebmi.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/weightRecord")
public class WeightRecordController {

    @Autowired
    WeightRecordService weightRecordService;

    @GetMapping("/GetWeightListByTimeSoltAndUserId")
    public Result GetWeightListByTimeSoltAndUserId(@RequestParam Integer userId,
                                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String startDate,
                                                   @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String endDate) {


        if (userId == null) {
            throw new MyException(-1, "用户Id不能为空");
        }
        if (userId < 0) {
            throw new MyException(-9, "用户Id不能为负");
        }
        if (startDate.equals("")) {
            throw new MyException(-2, "开始日期不能为空");
        }
        if (endDate.equals("")) {
            throw new MyException(-3, "结束日期不能为空");
        }
        Integer i = startDate.compareTo(endDate);
        if (i > 0) {
            throw new MyException(-4, "开始日期不能大于结束日期");
        }

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(d);
        Integer StartD = startDate.compareTo(str);
        if (StartD > 0) {
            throw new MyException(-7, "开始日期不能大于当前日期");
        }
        Integer endD = endDate.compareTo(str);
        if (endD > 0) {
            throw new MyException(-6, "结束日期不能大于当前日期");
        }
        List<WeightRecord> list = weightRecordService.GetWeightListByTimeSoltAndUserId(userId, startDate, endDate);
        return Result.success(list);

    }

    @GetMapping("/addWeightRecord")
    public Result addWeightRecord(@RequestParam Integer userId,
                                  @RequestParam Integer weight,
                                  @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String addDate) {
        if (userId == null) {
            throw new MyException(-1, "用户Id不能为空");
        }
        if (userId < 0) {
            throw new MyException(-6, "用户Id不能为负");
        }
        if (weight == null) {
            throw new MyException(-3, "体重不能为空");
        }
        if (weight < 0) {
            throw new MyException(-5, "体重不能为负");
        }
        if (addDate.equals("")) {
            throw new MyException(-2, "记录日期不能为空");
        }
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str = sdf.format(d);
        Integer StartD = addDate.compareTo(str);
        if (StartD > 0) {
            throw new MyException(-8, "记录日期不能大于当前日期");
        }
        weightRecordService.addWeightRecord(userId, weight, addDate);
        return Result.success(null);
    }

    @GetMapping("/updateWeightRecordByRecordId")
    public Result updateWeightRecordByRecordId(@RequestParam Integer recordId,
                                               @RequestParam Integer weight) {
        if (recordId == null) {
            throw new MyException(-1, "记录Id不能为空");
        }
        if (recordId < 0) {
            throw new MyException(-5, "记录Id不能为负");
        }
        if (weight == null) {
            throw new MyException(-2, "体重不能为空");
        }
        if (weight < 0) {
            throw new MyException(-4, "体重不能为负");
        }
        weightRecordService.updateWeightRecordByRecordId(recordId, weight);
        return Result.success(null);
    }
}
