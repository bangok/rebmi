package com.maamcare.rebmi.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.maamcare.rebmi.vo.ErrMap;
import com.maamcare.rebmi.vo.Result;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/weightRecord")
public class WeightRecordController {



    @GetMapping("/GetWeightListByTimeSoltAndUserId")
    public Result GetWeightListByTimeSoltAndUserId(@RequestParam  Integer userId,
                                                 @RequestParam  @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
                                                 @RequestParam  @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate){

        JSONArray array = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",1);
        jsonObject.put("userId",1);
        jsonObject.put("weight","800");
        jsonObject.put("record_date","2019-12-15");
        array.add(jsonObject);
        array.add(jsonObject);
        return Result.builder()
                .status(1)
                .err(new ErrMap(0,""))
                .data(array)
                .build();

    }
    @GetMapping("/addWeightRecord")
    public Result addWeightRecord(@RequestParam  Integer userId,
                                @RequestParam  Integer weight,
                                @RequestParam  @DateTimeFormat(pattern="yyyy-MM-dd") Date addDate){
        return Result.builder()
                .status(1)
                .err(new ErrMap(0,""))
                .data(null)
                .build();
    }
    @GetMapping("/updateWeightRecord")
    public Result updateWeightRecord(@RequestParam  Integer recordId,
                                   @RequestParam  Integer weight){
        return Result.builder()
                .status(1)
                .err(new ErrMap(0,""))
                .data(null)
                .build();
    }
}
