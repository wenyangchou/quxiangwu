package com.ruoyi.project.system.order.constant;

/**
 * author:zwy
 * Date:2019-10-19
 * Time:20:06
 */
public class OrderStatusConstant {

    public final static Integer NOT_PAY = 0;

    public final static Integer PAYED_BEFORE_MEET = 1;

    public final static Integer MEETED_BEFORE_SCAN_CODE = 2;

    public final static Integer SCANNED_BEFORE_COMMENT = 3;

    public final static Integer COMMENTED = 5;

    public final static Integer FINISHED = 6;
}
