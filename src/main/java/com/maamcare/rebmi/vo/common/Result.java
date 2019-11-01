package com.maamcare.rebmi.vo.common;
import com.maamcare.rebmi.vo.ErrMap;

public class Result {
    public Integer status;
    public ErrMap err;
    public Object data;


    public Result(Integer status, ErrMap err, Object data) {
        this.status = status;
        this.err = err;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + status +
                ", err=" + err +
                ", data=" + data +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ErrMap getErr() {
        return err;
    }

    public void setErr(ErrMap err) {
        this.err = err;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result success(Object o){
        return new Result(1,new ErrMap(0,""),o);
    }

    public static Result fail(ErrMap err){
        return new Result(0,err,null);
    }
}
