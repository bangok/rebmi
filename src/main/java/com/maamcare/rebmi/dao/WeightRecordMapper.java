package com.maamcare.rebmi.dao;

import com.maamcare.rebmi.po.WeightRecord;

import java.util.Date;
import java.util.List;

public interface WeightRecordMapper {
    public List<WeightRecord> GetWeightListByTimeSoltAndUserId(Integer userId, String startDate, String endDate);
    public WeightRecord addWeightRecord(Integer userId, Integer weight,String addDate);
    public WeightRecord updateWeightRecord(Integer recordId, Integer weight);
    public WeightRecord getWeightByUserIdAndDate(Integer userId, String AnyDate);

}
