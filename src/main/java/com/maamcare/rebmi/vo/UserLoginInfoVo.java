package com.maamcare.rebmi.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginInfoVo {
    @NotNull(message = "-1,用户名为空")
    @Size(max = 8,message = "-2,用户名长度不能大于8")
    @Size(min = 3,message = "-3,用户名长度不能小于3")
    @Pattern(regexp = "[a-zA-Z0-9]+$",message = "-4,用户名只允许英文、数字")
    public String username;

    @NotNull(message = "-5,密码为空")
    @Size(min = 6,max=6,message = "-6,密码长度必须为6位")
    @Pattern(regexp = "[a-zA-Z0-9]+$",message = "-7,密码只允许英文、数字")
    public String password;
}
