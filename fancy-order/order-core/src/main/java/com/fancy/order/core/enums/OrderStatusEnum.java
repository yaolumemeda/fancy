package com.fancy.order.core.enums;

public enum OrderStatusEnum {
  /* 
0   初始生成订单，支付模块创建支付中订单，若支付成功推到状态1，支付失败（如3min内未支付）推到状态10
1	待生产：已经完成预订
2	生产中：已经开始生产
3	待付款：前台显示支付尾款按钮，点击唤起微信支付
4	待发货：已经购买成功
5	已发货：已经完成发货，前台显示确认收货按钮
6	已完成-确认收货
7	已完成-确认超时
8	已完成-预订失败
9	已完成-生产失败
10	订单未完成支付，一种为初始订单未支付预订1元；第二种为限定时间内未支付尾款，主动退1元钱*/
    INITIAL("100","初始生成订单"),
    TO_BE_PRODUCED("101","待生产"),
    IN_PRODUCTION("102","生产中"),
    PENDING_PAYMENT("103","待付款"),
    TO_BE_DELIVERED("104","待发货"),
    DELIVERED("105","已发货"),
    COMPLETED_CONFIRMED_RECEIPT("201","已完成-确认收货"),
    COMPLETED_CONFIRMATION_TIMEOUT("202","已完成-确认超时"),
    COMPLETED_RESERVATION_FAILTURE("301","已完成-预订失败"),
    COMPLETED_PRODUCTION_FAILURE("302","已完成-生产失败"),
    UNPAID("400","未支付");

    private String code;
    private String msg;

    OrderStatusEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
