package com.ruoyi.project.system.message.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.message.domain.Message;
import com.ruoyi.project.system.message.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author:zwy
 * Date:2019-08-17
 * Time:23:09
 */
@RestController
@RequestMapping("/service/message")
public class MessageController extends BaseController {

    @Autowired
    private IMessageService messageService;

    @GetMapping("/getThingMessage")
    public TableDataInfo getThingMessage(Long thingId){
        startPage();
        return getDataTable(messageService.getByThingId(thingId));
    }

    @PostMapping("/addMessage")
    public AjaxResult addMessage(@RequestBody Message message){
        return toAjax(messageService.addMessage(message));
    }
}
