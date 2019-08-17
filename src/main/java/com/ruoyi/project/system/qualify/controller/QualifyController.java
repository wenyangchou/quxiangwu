package com.ruoyi.project.system.qualify.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.qualify.domain.Qualify;
import com.ruoyi.project.system.qualify.service.IQualifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
