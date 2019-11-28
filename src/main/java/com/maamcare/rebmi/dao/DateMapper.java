package com.maamcare.rebmi.dao;

import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
@Transactional
public interface DateMapper {
    @Insert("insert into record (userid,record_date,weight) values(#{userId},#{date},#{weight})")
    boolean addRecord(Integer userId, Integer weight, Date date);
}
