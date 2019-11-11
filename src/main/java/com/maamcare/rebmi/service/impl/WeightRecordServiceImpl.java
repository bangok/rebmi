package com.maamcare.rebmi.service.impl;


import com.maamcare.rebmi.dao.UserMapper;
import com.maamcare.rebmi.dao.WeightRecordMapper;
import com.maamcare.rebmi.exception.MyException;
import com.maamcare.rebmi.po.User;
import com.maamcare.rebmi.po.WeightRecord;
import com.maamcare.rebmi.service.WeightRecordService;
import org.omg.CORBA.NO_IMPLEMENT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service
public class WeightRecordServiceImpl implements WeightRecordService {
    @Autowired
    WeightRecordMapper weightRecordMapper;
    @Autowired
    UserMapper userMapper;

    @Override
    public List<WeightRecord> GetWeightListByTimeSoltAndUserId(Integer userId, String startDate, String endDate) {
        User user  =  userMapper.getUserByUserId(userId);

        if (user == null) {
            throw new MyException(-5, "用户不存在");
        }
        List<WeightRecord>  list = weightRecordMapper.GetWeightListByTimeSoltAndUserId(userId, startDate, endDate) ;

        return list;
    }

    @Override
    public boolean addWeightRecord(Integer userId, Integer weight, String addDate) {
        User user  =  userMapper.getUserByUserId(userId);
        if (user == null) {
            throw new MyException(-5, "用户不存在");
        }
        WeightRecord weightRecord = new WeightRecord() ;
        Integer integer = weightRecordMapper.addWeightRecord(userId,weight,addDate);
        boolean isSuccess = true;
        if (integer <= 0)
        {
            isSuccess = false;
            throw new MyException(-7, "添加体重失败");
        }
        return isSuccess;
    }

    @Override
    public boolean updateWeightRecordByRecordId(Integer recordId, Integer weight) {

        WeightRecord weightRecord =  weightRecordMapper.GetWeightRecordByRecordId(recordId);
        if (weightRecord ==null){
            throw new MyException(-3, "体重记录不存在");
        }
        Integer integer =  weightRecordMapper.updateWeightRecordByRecordId(recordId,weight);
        boolean isSuccess = true;
        if (integer <= 0)
        {
            isSuccess = false;
            throw new MyException(-6, "更新体重信息失败");
        }
        return isSuccess;
    }
}
