package com.fancy.order.facade.request;

public class OrderResquest {

    //spuId，后期通过此id拿到sku图片，spu名称，价格，颜色，尺寸
    private String spuId;
    //数量
    private Integer count;
   // 地址
    private String address;
    //姓名
    private String name;
    //电话
    private String telephone;

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
}
