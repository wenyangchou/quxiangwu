package com.ruoyi.project.system.order.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.order.domain.Order;
import com.ruoyi.project.system.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author:zwy
 * Date:2019-08-18
 * Time:00:41
 */
@RestController
@RequestMapping("/service/order")
public class OrderController extends BaseController {

    @Autowired
    private IOrderService orderService;

    @PostMapping("/addOrder")
    public AjaxResult addOrder(@RequestBody Order order){
        return toAjax(orderService.addOrder(order));
    }

    @PostMapping("/updateOrder")
    public AjaxResult updateOrder(@RequestBody Order order){
        return toAjax(orderService.updateOrder(order));
    }
}
