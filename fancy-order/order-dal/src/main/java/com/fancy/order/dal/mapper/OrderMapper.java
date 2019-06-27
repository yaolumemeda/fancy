package com.fancy.order.dal.mapper;


import com.fancy.order.dal.dao.OrderDao;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    int addOrder(OrderDao order);
}
