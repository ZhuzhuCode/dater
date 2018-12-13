package com.zjr.dater.common;

import java.io.Serializable;


/**
 * Created by zhujr on 2018/9/27.
 * json格式通用对象
 */
public class ResultObj implements Serializable{
    private static final long serialVersionUID = -1982541672022823078L;

    private String code;
    private String msg;
    private Integer count;
    private Object data;

    public interface CodeC{
        public final static String SUCCESS = "0";
        public final static String FAIL = "1";
    }

    public ResultObj() {
    }

    public ResultObj(String code, String msg, Integer count, Object data) {
        this.code = code;
        this.msg = msg;
        this.count = count;
        this.data = data;
    }

    public ResultObj(Integer count, Object data) {
        this.code = CodeC.SUCCESS;
        this.msg = "";
        this.count = count;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
