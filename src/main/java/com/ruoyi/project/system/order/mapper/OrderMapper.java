package com.ruoyi.project.system.order.mapper;

import com.ruoyi.project.system.order.domain.Order;

public interface OrderMapper {

    int insertOrder(Order order);

    int updateOrder(Order order);
}
