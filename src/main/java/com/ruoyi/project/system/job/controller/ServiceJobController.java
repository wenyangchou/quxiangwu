package com.ruoyi.project.system.job.controller;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.job.service.IServiceJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author:zwy
 * Date:2019-08-17
 * Time:21:44
 */
@RestController
@RequestMapping("/service/job")
public class ServiceJobController extends BaseController {

    @Autowired
    private IServiceJobService jobService;

    @GetMapping("/getJob")
    public TableDataInfo getUserJob(){
        Long userId = ShiroUtils.getUserId();
        startPage();
        return getDataTable(jobService.getJobByUserId(userId));
    }

    @GetMapping("/coinDuty")
    public TableDataInfo coinDuty(){
        startPage();
        return getDataTable(jobService.getCoinDuty());
    }

    @PostMapping("/signJob")
    public AjaxResult signJob(Long jobId,Integer jobType){
        return toAjax(jobService.signJob(jobId,jobType));
    }
}
