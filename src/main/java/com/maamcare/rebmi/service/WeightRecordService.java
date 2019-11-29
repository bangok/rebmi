package com.maamcare.rebmi.service;


import com.maamcare.rebmi.po.WeightRecord;


import java.util.List;

public interface WeightRecordService {
    List<WeightRecord> GetWeightListByTimeSoltAndUserId(Integer userId, String startDate, String endDate);
    boolean addWeightRecord(Integer userId, Integer weight,String addDate);
    boolean updateWeightRecordByRecordId(Integer recordId, Integer weight);

}
