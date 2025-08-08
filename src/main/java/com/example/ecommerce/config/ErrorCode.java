package com.example.ecommerce.config;

public enum ErrorCode {
    // 通用错误‌:ml-citation{ref="7" data="citationList"}
    ORDER_ERROR("1001", "订单商品不能为空"),
    AUTH_FAILED("4002", "身份验证失败"),
    STOCK_SHORTAGE("5001", "库存不足"),

    // 业务模块错误（示例）
    ORDER_PAY_TIMEOUT("6001", "订单支付超时");

    private final String code;
    private final String desc;

    ErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() { return code; }
    public String getDesc() { return desc; }
}

