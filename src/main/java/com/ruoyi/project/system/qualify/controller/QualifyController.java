package com.ruoyi.project.system.qualify.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.qualify.domain.Qualify;
import com.ruoyi.project.system.qualify.service.IQualifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * author:zwy
 * Date:2019-08-18
 * Time:01:43
 */
@Controller
@RequestMapping("/service/qualify")
public class QualifyController extends BaseController {

    @Autowired
    private IQualifyService qualifyService;

    private final static String PREFIX = "/service/qualify";

    @GetMapping()
    public String qualify(){
        return PREFIX+"/qualify";
    }

    @PostMapping("/waitList")
    @ResponseBody
    public TableDataInfo getWaitQualify(){
        startPage();
        return getDataTable(qualifyService.getWaitQualify());
    }

    @PostMapping("/qualifyList")
    @ResponseBody
    public TableDataInfo getAllQualify(){
        startPage();
        return getDataTable(qualifyService.getAllQualify());
    }


    @PostMapping("/addQualify")
    @ResponseBody
    public AjaxResult addQualify(@RequestBody Qualify qualify){
        return toAjax(qualifyService.addQualify(qualify));
    }

    @PostMapping("/updateQualify")
    @ResponseBody
    public AjaxResult updateQualify(@RequestBody Qualify qualify){
        return toAjax(qualifyService.updateQualify(qualify));
    }

    @PostMapping("/setConfirm")
    @ResponseBody
    public AjaxResult setConfirm(Integer type,String img){
        return toAjax(qualifyService.setConfirm(type,img));
    }

    @GetMapping("/getConfirmHistory")
    @ResponseBody
    public TableDataInfo getConfirmHistory(Integer type){
        startPage();
        return getDataTable(qualifyService.getConfirmHistory(type));
    }

    @GetMapping("/getResult")
    @ResponseBody
    public AjaxResult getConfirmResult(Integer type){
        return AjaxResult.success().put("data",qualifyService.getStatus(type));
    }
}
