package com.ruoyi.project.system.quba.controller;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.quba.service.IQubaService;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author:zwy
 * Date:2019-08-14
 * Time:22:52
 */
@RestController
@RequestMapping("/service/quba")
public class QubaController extends BaseController {

    @Autowired
    private IQubaService qubaService;

    @GetMapping("/getMyQuba")
    public TableDataInfo getMyQuba(){
        startPage();
        return getDataTable(qubaService.getUserQuba());
    }

    @GetMapping("/getAllQuba")
    public TableDataInfo getAllQuba(){
        startPage();
        return getDataTable(qubaService.getAll());
    }

    @PostMapping("/joinQuba")
    public AjaxResult joinQuba(Long qubaId){
      return toAjax(qubaService.insertQubaUser(ShiroUtils.getUserId(),qubaId));
    }

    @GetMapping("/getIsSigninToday")
    public AjaxResult getIsSigninToday(Long qubaId){
        return toAjax(qubaService.getQubaSignInByUserId(ShiroUtils.getUserId(),qubaId));
    }

    @GetMapping("/signQuba")
    public AjaxResult signQuba(Long qubaId){
        int isSigninToday = qubaService.getQubaSignInByUserId(ShiroUtils.getUserId(),qubaId);
        if (isSigninToday>0){
            return AjaxResult.error("重复签到");
        }else{
            return toAjax(qubaService.insertQubaSignIn(ShiroUtils.getUserId(),qubaId)) ;
        }
    }

    @GetMapping("/getQubaMember")
    public TableDataInfo getQubaMember(Long qubaId)
    {   startPage();
        return getDataTable(qubaService.getQubaMemberByQubaId(qubaId));
    }

    @GetMapping("/getQubaOwner")
    public AjaxResult getQubaOwner(Long qubaId){
        return success().put("owner",qubaService.getQubaOwner(qubaId));
    }
}
