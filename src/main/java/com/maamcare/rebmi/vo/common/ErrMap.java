package com.maamcare.rebmi.vo.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrMap {
    public Integer code;
    public String msg;
}