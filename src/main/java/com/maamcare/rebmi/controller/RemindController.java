package com.maamcare.rebmi.controller;

import com.alibaba.fastjson.JSONObject;
import com.maamcare.rebmi.vo.ErrMap;
import com.maamcare.rebmi.vo.Result;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/remind")
public class RemindController {
    @GetMapping("/getWeightByUserIdAndDate")
    public Result getWeightByUserIdAndDate(@RequestParam Integer userId,
                                         @RequestParam  @DateTimeFormat(pattern="yyyy-MM-dd") Date anyDate){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",1);
        jsonObject.put("userId",1);
        jsonObject.put("weight","800");
        jsonObject.put("record_date","2019-12-15");

        return Result.builder()
                .status(1)
                .err(new ErrMap(0,""))
                .data(jsonObject)
                .build();
    }
}
