package com.maamcare.rebmi.service.impl;


import com.maamcare.rebmi.dao.DateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DateService {

    @Autowired
    DateMapper dateMapper;

    public boolean addDate(){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date();
        try {
            date = sdf.parse("1919-11-28");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date);
        return dateMapper.addRecord(7,800,date);
    }
}
