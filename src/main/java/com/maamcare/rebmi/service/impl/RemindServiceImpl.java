package com.maamcare.rebmi.service.impl;


import com.maamcare.rebmi.dao.UserMapper;
import com.maamcare.rebmi.dao.WeightRecordMapper;
import com.maamcare.rebmi.exception.MyException;
import com.maamcare.rebmi.po.User;
import com.maamcare.rebmi.po.WeightRecord;
import com.maamcare.rebmi.service.RemindService;
import com.maamcare.rebmi.vo.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RemindServiceImpl implements RemindService {

    @Autowired
    WeightRecordMapper weightRecordMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public WeightRecord getWeightByUserIdAndDate(Integer userId, String anyDate) {


        User user  =  userMapper.getUserByUserId(userId);
        if (user == null)
        {
           throw new  MyException(-5,"用户不存在");
        }
        WeightRecord weightRecord = weightRecordMapper.getWeightByUserIdAndDate(userId,anyDate);

        return weightRecord;
    }
}
