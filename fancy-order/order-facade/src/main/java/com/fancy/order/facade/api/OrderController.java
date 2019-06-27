package com.fancy.order.facade.api;


import com.fancy.order.core.exception.OrderException;
import com.fancy.order.dal.dao.OrderDao;
import com.fancy.order.dal.dao.UserInfoDao;
import com.fancy.order.facade.request.OrderResquest;
import com.fancy.order.facade.result.OrderAddResult;
import com.fancy.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;



import static com.fancy.order.core.enums.OrderResultEnum.SUCCESS;
import static com.fancy.order.core.enums.OrderResultEnum.USER_NOLOGIN;


@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/add")
    public OrderAddResult applyOrder(@RequestBody OrderResquest request, HttpSession session) {
       UserInfoDao user=(UserInfoDao)session.getAttribute("user");
       /*校验用户是否登录,调用小程序登录api，获得openID*/
        if(user==null){return new OrderAddResult(USER_NOLOGIN.getCode(),USER_NOLOGIN.getMsg());}
        /*校验用户账户是否正常*/
        /*调用商品模块，校验商品是否可预订，返回商品名称（微信支付需要body）*/
        OrderDao order=new OrderDao();
        order.setSpuId(request.getSpuId());
        order.setCount(request.getCount());
        order.setAddress(request.getAddress());
        order.setName(request.getName());
        order.setTelephone(request.getTelephone());
        order.setUserId(user.getUid());
        try{
            orderService.addOrder(order);
            /*调用统一下单api*/
            return new OrderAddResult(SUCCESS.getCode(),SUCCESS.getMsg());
        }catch (OrderException e){
            return new OrderAddResult(e.getOrderExceptionEnum().getCode(),e.getOrderExceptionEnum().getDesc());
        }

    }
}
