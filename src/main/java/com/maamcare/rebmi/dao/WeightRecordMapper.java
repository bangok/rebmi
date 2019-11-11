package com.maamcare.rebmi.dao;

import com.maamcare.rebmi.po.WeightRecord;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Repository
@Transactional
public interface WeightRecordMapper {
    public List<WeightRecord> GetWeightListByTimeSoltAndUserId(Integer userId, String startDate, String endDate);
    public Integer addWeightRecord(Integer userId, Integer weight,String addDate);
    public Integer updateWeightRecordByRecordId(Integer recordId, Integer weight);
    public WeightRecord getWeightByUserIdAndDate(Integer userId, String anyDate);
    public WeightRecord GetWeightRecordByRecordId(Integer recordId);

}
