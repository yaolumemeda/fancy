package com.fancy.order.service;


import com.fancy.order.dal.dao.OrderDao;

public interface OrderService {
    public int addOrder(OrderDao order);
}
