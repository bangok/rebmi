package com.maamcare.rebmi.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterInfoVo {
    @Max(value = 8,message = "-2,用户名长度不能大于8")
    public String username;
    public String password;
    public Integer height;
}
