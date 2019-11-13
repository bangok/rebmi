package com.maamcare.rebmi.exception;
import com.maamcare.rebmi.vo.common.ErrMap;
import com.maamcare.rebmi.vo.common.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 全局异常处理
 * */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler(value = BindException.class)
    @ResponseBody
    public Result bindExceptionHandler(BindException e){
        ErrMap errMap = new ErrMap();
        String message = null;
        Integer code = null;
        List<ObjectError> elist =e.getAllErrors();
        String str = elist.get(0).getDefaultMessage();
        String[] arr = str.split(",");
        errMap.setCode(Integer.valueOf(arr[0]));
        errMap.setMsg(arr[1]);
        return Result.fail(errMap);
    }

    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public Result myExceptionHandler(MyException e){
        ErrMap errMap = new ErrMap();
        String message = null;
        Integer code = null;
        //判断是否为系统自定义异常。
        message = ((MyException) e).getMsg();
        code = ((MyException) e).getCode();
        errMap.setMsg(message);
        errMap.setCode(code);
        return Result.fail(errMap);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result ExceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws IOException {
        ErrMap errMap = new ErrMap();
        String message = null;
        Integer code = null;
        System.out.println(e.toString());
        System.out.println(e.getMessage());
        errMap.setMsg(e.getMessage());
        errMap.setCode(-200);
        return Result.fail(errMap);

    }


}

