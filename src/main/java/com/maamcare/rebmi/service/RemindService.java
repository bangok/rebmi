package com.maamcare.rebmi.service;


import com.maamcare.rebmi.po.WeightRecord;


public interface RemindService {
    WeightRecord getWeightByUserIdAndDate(Integer userId, String anyDate) ;
}
