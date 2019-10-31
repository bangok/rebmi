package com.maamcare.rebmi.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeightRecord {
    public Integer id;
    public Integer userId;
    public Integer weight;
    public String record_date;

}
