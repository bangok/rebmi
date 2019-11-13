package com.maamcare.rebmi.vo;

import com.maamcare.rebmi.vo.common.ErrMap;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * 已废弃，尽量使用common下的Result
 * */


@Builder
@Setter
@Getter
@ToString
public class Result {
    public Integer status;
    public ErrMap err;
    public Object data;
}
