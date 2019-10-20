package com.ruoyi.project.system.money.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.money.service.IMoneyHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * author:zwy
 * Date:2019-10-20
 * Time:16:41
 */
@RestController
@RequestMapping("/service/money")
public class MoneyController extends BaseController {

    @Autowired
    private IMoneyHistoryService moneyHistoryService;

    @GetMapping("/coinChange")
    public TableDataInfo getCoinChange(){
        startPage();
        return getDataTable(moneyHistoryService.getUserMoneyHistory());
    }
}
