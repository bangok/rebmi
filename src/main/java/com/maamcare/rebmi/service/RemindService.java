package com.maamcare.rebmi.service;

import com.maamcare.rebmi.dto.MyDto;
import com.maamcare.rebmi.po.WeightRecord;

import java.util.Date;

public interface RemindService {
    public WeightRecord getWeightByUserIdAndDate(Integer userId, String anyDate) ;
}
