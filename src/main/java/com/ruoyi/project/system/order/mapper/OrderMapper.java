package com.ruoyi.project.system.order.mapper;

import com.ruoyi.project.system.order.domain.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {

    int insertOrder(Order order);

    int updateOrder(Order order);

    Order getById(Long orderId);
}
