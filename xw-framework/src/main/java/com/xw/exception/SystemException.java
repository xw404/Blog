package com.xw.exception;

import com.xw.enums.AppHttpCodeEnum;

/**
 * @Author 小吴
 * @Date 2023/05/31 9:24
 * @Version 1.0
 */
public class SystemException extends RuntimeException{

    private int code;

    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    //将枚举类型传参
    public SystemException(AppHttpCodeEnum httpCodeEnum) {
        super(httpCodeEnum.getMsg());
        this.code = httpCodeEnum.getCode();
        this.msg = httpCodeEnum.getMsg();
    }

}
