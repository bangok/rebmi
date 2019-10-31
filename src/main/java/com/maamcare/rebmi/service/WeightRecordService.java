package com.maamcare.rebmi.service;

import com.maamcare.rebmi.dto.MyDto;
import com.maamcare.rebmi.po.WeightRecord;

import java.util.Date;
import java.util.List;

public interface WeightRecordService {
    public List<WeightRecord> GetWeightListByTimeSoltAndUserId(Integer userId, String startDate, String endDate);
    public WeightRecord addWeightRecord(Integer userId, Integer weight,String addDate);
    public WeightRecord updateWeightRecord(Integer recordId, Integer weight);

}
