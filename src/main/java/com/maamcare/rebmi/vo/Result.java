package com.maamcare.rebmi.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;


@Builder
@Setter
@Getter
@ToString
public class Result {
    public Integer status;
    public ErrMap err;
    public Object data;
}
