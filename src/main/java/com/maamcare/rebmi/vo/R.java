package com.maamcare.rebmi.vo;

public class R {
    public Integer code;
    public ErrMap err;
    public Object data;

    public R() {
    }

    public R(Integer code, ErrMap err, Object data) {
        this.code = code;
        this.err = err;
        this.data = data;
    }

    @Override
    public String toString() {
        return "R{" +
                "code=" + code +
                ", err=" + err +
                ", data=" + data +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public ErrMap getErr() {
        return err;
    }

    public Object getData() {
        return data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setErr(ErrMap err) {
        this.err = err;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static R success(Object o){
        return new R(1,new ErrMap(0,""),o);
    }

    public static R fail(ErrMap err){
        return new R(1,err,null);
    }
}
