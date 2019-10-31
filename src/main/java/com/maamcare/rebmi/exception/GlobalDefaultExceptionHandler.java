package com.maamcare.rebmi.exception;

import com.maamcare.rebmi.vo.ErrMap;
import com.maamcare.rebmi.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result MyExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
        ErrMap errMap = new ErrMap();

        String message = null;
        Integer code = null;
        //判断是否为系统自定义异常。
        if (e instanceof MyException) {
            message = ((MyException) e).getMsg();
            code = ((MyException) e).getCode();
            errMap.setMsg(message);
            errMap.setCode(code);
            return Result.builder()
                    .status(0)
                    .err(errMap)
                    .data(null)
                    .build();
        }else
        {

            errMap.setMsg(e.getMessage());
            errMap.setCode(-200);
                   return Result.builder()
                           .status(0)
                           .err(errMap)
                           .data(null)
                           .build();
        }
    }

}

