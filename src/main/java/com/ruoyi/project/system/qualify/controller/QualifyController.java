package com.ruoyi.project.system.qualify.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.qualify.domain.Qualify;
import com.ruoyi.project.system.qualify.service.IQualifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author:zwy
 * Date:2019-08-18
 * Time:01:43
 */
@RestController
@RequestMapping("/service/qualify")
public class QualifyController extends BaseController {

    @Autowired
    private IQualifyService qualifyService;

    @PostMapping("/addQualify")
    public AjaxResult addQualify(@RequestBody Qualify qualify){
        return toAjax(qualifyService.addQualify(qualify));
    }

    @PostMapping("/updateQualify")
    public AjaxResult updateQualify(@RequestBody Qualify qualify){
        return toAjax(qualifyService.updateQualify(qualify));
    }

    @PostMapping("/setConfirm")
    public AjaxResult setConfirm(Integer type,String img){
        return toAjax(qualifyService.setConfirm(type,img));
    }

    @GetMapping("/getConfirmHistory")
    public TableDataInfo getConfirmHistory(Integer type){
        startPage();
        return getDataTable(qualifyService.getConfirmHistory(type));
    }

    @GetMapping("/getResult")
    public AjaxResult getConfirmResult(Integer type){
        return AjaxResult.success().put("data",qualifyService.getStatus(type));
    }
}
