package com.fancy.order.core.enums;

public enum OrderExceptionEnum {
    ORDER_CREATION_EXCEPTION("ORDER_CREATION_EXCEPTION", "订单创建失败");


    private String code;
    private String desc;

    OrderExceptionEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
