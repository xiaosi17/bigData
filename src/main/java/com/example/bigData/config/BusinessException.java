package com.example.bigData.config;

public class BusinessException extends RuntimeException {
    private final String code;  // 业务错误码‌:ml-citation{ref="4,7" data="citationList"}
    private final String msg;   // 可读性错误描述‌:ml-citation{ref="2,4" data="citationList"}

    // 基础构造器
    public BusinessException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    // 带原始异常构造器
    public BusinessException(String code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }

    // Getter
    public String getCode() { return code; }
    public String getMsg() { return msg; }
}

