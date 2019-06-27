package com.fancy.order.core.enums;

public enum OrderResultEnum {

    USER_NOLOGIN("ORDER000001", "用户未登录"),
    USER_DISABLE("ORDER000002","用户账号不可用"),
    SPU_NORESERVE("ORDER000003","商品不可预订"),
    SUCCESS("ORDER000004","订单创建成功");


    /** 向前端暴露的错误码 */
    private String code;

    /** 向前端暴露的信息 */
    private String msg;

    OrderResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
