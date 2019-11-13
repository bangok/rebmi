package com.maamcare.rebmi.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterInfoVo {

    @NotNull(message = "-1,用户名为空")
    @Size(max = 8,message = "-2,用户名长度不能大于8")
    @Size(min = 3,message = "-3,用户名长度不能小于3")
    @Pattern(regexp = "[a-zA-Z0-9]+$",message = "-4,用户名只允许英文、数字")
    public String username;

    @NotNull(message = "-5,密码为空")
    @Size(min = 6,max=6,message = "-6,密码只允许6位")
    @Pattern(regexp = "[a-zA-Z0-9]+$",message = "-7,密码只允许英文、数字")
    public String password;

    @NotNull(message = "-8,身高为空")
    @Min(value = 1,message = "-9,身高为不能小于等于0")
    public Integer height;
}
