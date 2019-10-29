package com.maamcare.rebmi.dto;

import lombok.*;

@Builder
@Setter
@Getter
@ToString
/**
 * service层结果统一封装返回
 * */
public class MyDto {
    public Integer code;
    public String msg;
    public Object data;
}
