package com.ruoyi.project.system.order.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.common.OrderStatusConstant;
import com.ruoyi.project.system.order.domain.ConfirmTipDTO;
import com.ruoyi.project.system.order.domain.Order;
import com.ruoyi.project.system.order.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    public String addOrder(@RequestBody ConfirmTipDTO confirmTipDTO){
        return dealOrderId(orderService.addOrder(confirmTipDTO));
    }

    @PostMapping("/updateOrder")
    public AjaxResult updateOrder(@RequestBody Order order){
        return toAjax(orderService.updateOrder(order));
    }


    @GetMapping("/getTipInfo")
    public ConfirmTipDTO getByTipId(Long tipId){
        return orderService.getConfirmTip(tipId);
    }

    @PostMapping("/closeTip")
    public AjaxResult closeTip(Long tipId){
        Order order = new Order();
        order.setId(tipId);
        order.setStatus(OrderStatusConstant.FINISHED);
        return toAjax(orderService.updateOrder(order));
    }

    @PostMapping("/changeStatus")
    public AjaxResult changeTip(Long tipId,Integer targetStatus){
        Order order = new Order();
        order.setId(tipId);
        order.setStatus(targetStatus);
        return toAjax(orderService.updateOrder(order));
    }

    @PostMapping("/scanTip")
    public AjaxResult scanTip(Long tipId){
        Order order = new Order();
        order.setId(tipId);
        order.setStatus(OrderStatusConstant.SCANNED_BEFORE_COMMENT);
        return toAjax(orderService.updateOrder(order));
    }

    private String dealOrderId(Long orderId){
        return "{\"tipId\":\""+orderId+"\"}";
    }

}
