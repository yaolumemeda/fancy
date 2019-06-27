package com.fancy.order.core.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class OrderCodeUtil {
    /*
     * 订单号生成器，生成18位字符串编码
     * 生成规则 14位时间戳+4位随机数
     *
     * */
    public static String createOrderNumber(){

        StringBuffer buffer = new StringBuffer();
        buffer.append(getDateTime());
        buffer.append(getRandom(4));
        return buffer.toString();
    }

    /*14位时间戳*/
    public static String getDateTime() {
        // 获得当前日期时间
        LocalDateTime now = LocalDateTime.now();
        // 格式化日期时间
        DateTimeFormatter dateToStrFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return( dateToStrFormatter.format(now));

    }

    /*4位随机数*/
    public static String getRandom(int len) {

        Random random = new Random();
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < len; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    /*public static void main(String[] args) {
        //获取订单编码
        System.out.println(createOrderNumber());

    }
*/
}
