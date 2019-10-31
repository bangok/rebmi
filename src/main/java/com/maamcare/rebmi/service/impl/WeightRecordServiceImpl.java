package com.maamcare.rebmi.service.impl;


import com.maamcare.rebmi.po.WeightRecord;
import com.maamcare.rebmi.service.WeightRecordService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class WeightRecordServiceImpl implements WeightRecordService {

    @Override
    public List<WeightRecord> GetWeightListByTimeSoltAndUserId(Integer userId, String startDate, String endDate) {
        return null;
    }

    @Override
    public WeightRecord addWeightRecord(Integer userId, Integer weight, String addDate) {
        return null;
    }

    @Override
    public WeightRecord updateWeightRecord(Integer recordId, Integer weight) {
        return null;
    }
}
