package com.fancy.order.service.impl;


import com.fancy.order.core.enums.OrderExceptionEnum;
import com.fancy.order.core.exception.OrderException;
import com.fancy.order.core.utils.OrderCodeUtil;
import com.fancy.order.dal.dao.OrderDao;
import com.fancy.order.dal.mapper.OrderMapper;
import com.fancy.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.fancy.order.core.enums.OrderStatusEnum.INITIAL;


@Service
public class OrderServiceImpl implements OrderService {
  @Autowired
  private OrderMapper orderMapper;

    public int addOrder(OrderDao order){

        order.setOid(OrderCodeUtil.createOrderNumber());
        order.setStatus(INITIAL.getCode());
        order.setOrderCreateTime(LocalDateTime.now());
        order.setOrderUpdateTime(LocalDateTime.now());
        try {
            return orderMapper.addOrder(order);
        }catch (Exception e){
            e.printStackTrace();
            throw new OrderException(OrderExceptionEnum.ORDER_CREATION_EXCEPTION);
        }

    }

}
