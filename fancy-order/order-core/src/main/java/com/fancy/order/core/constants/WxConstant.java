package com.fancy.order.core.constants;

public interface WxConstant {
    //微信小程序appid
    public  String appId = "";
    //微信商户号
    public String mch_id="";
    //微信支付主体
    public String title = "";
    //微信小程序appsecret
    public String appSecret = "";
    //微信支付的商户密钥
    public  String key = "";
    //获取微信Openid的请求地址
    public String WxGetOpenIdUrl = "";
    //支付成功后的服务器回调url
    public String notify_url="";
    //签名方式
    public String SIGNTYPE = "MD5";
    //交易类型
    public  String TRADETYPE = "JSAPI";
    //微信统一下单接口地址
    public String pay_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public int readTimeoutMs=8000;
    public int connectTimeoutMs=6000;

}
