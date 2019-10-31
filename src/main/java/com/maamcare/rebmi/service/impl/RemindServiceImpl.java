package com.maamcare.rebmi.service.impl;

import com.maamcare.rebmi.dto.MyDto;
import com.maamcare.rebmi.po.WeightRecord;
import com.maamcare.rebmi.service.RemindService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RemindServiceImpl implements RemindService {

    @Override
    public WeightRecord getWeightByUserIdAndDate(Integer userId, String anyDate) {
        return null;
    }
}
