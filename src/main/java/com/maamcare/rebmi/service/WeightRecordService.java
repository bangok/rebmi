package com.maamcare.rebmi.service;


import com.maamcare.rebmi.po.WeightRecord;


import java.util.List;

public interface WeightRecordService {
    public List<WeightRecord> GetWeightListByTimeSoltAndUserId(Integer userId, String startDate, String endDate);
    public boolean addWeightRecord(Integer userId, Integer weight,String addDate);
    public boolean updateWeightRecordByRecordId(Integer recordId, Integer weight);

}
