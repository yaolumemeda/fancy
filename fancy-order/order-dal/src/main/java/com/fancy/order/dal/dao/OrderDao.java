package com.fancy.order.dal.dao;

import java.time.LocalDateTime;

public class OrderDao {

    private String oid;
    /*订单创建时间*/
    private LocalDateTime orderCreateTime;
    /*订单修改时间*/
    private LocalDateTime orderUpdateTime;
    /*预订付款时间*/
    private LocalDateTime reservePayTime;
    /*尾款付款时间*/
    private LocalDateTime tailPayTime;
    /*发货时间*/
    private LocalDateTime deliveryTime;
   /*收货时间*/
    private LocalDateTime receiveTime;
    /*预订款*/
    private Double reserveMoney;
    /*交易款*/
    private Double tradeMoney;
    /*状态*/
    private String status;
    /*地址*/
    private String address;
    /*姓名*/
    private String name;
    /*电话*/
    private String telephone;
    /*数量*/
    private Integer count;
    /*spuId，后期通过此id拿到sku图片，spu名称，价格，颜色，尺寸*/
    private String spuId;
    private String userId;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public LocalDateTime getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(LocalDateTime orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public LocalDateTime getOrderUpdateTime() {
        return orderUpdateTime;
    }

    public void setOrderUpdateTime(LocalDateTime orderUpdateTime) {
        this.orderUpdateTime = orderUpdateTime;
    }

    public LocalDateTime getReservePayTime() {
        return reservePayTime;
    }

    public void setReservePayTime(LocalDateTime reservePayTime) {
        this.reservePayTime = reservePayTime;
    }

    public LocalDateTime getTailPayTime() {
        return tailPayTime;
    }

    public void setTailPayTime(LocalDateTime tailPayTime) {
        this.tailPayTime = tailPayTime;
    }

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public LocalDateTime getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(LocalDateTime receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Double getReserveMoney() {
        return reserveMoney;
    }

    public void setReserveMoney(Double reserveMoney) {
        this.reserveMoney = reserveMoney;
    }

    public Double getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(Double tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
