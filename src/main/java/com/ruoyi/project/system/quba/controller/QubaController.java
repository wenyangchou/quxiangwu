package com.ruoyi.project.system.quba.controller;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.quba.domain.Quba;
import com.ruoyi.project.system.quba.service.IQubaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author:zwy
 * Date:2019-08-14
 * Time:22:52
 */
@Controller
@RequestMapping("/service/quba")
public class QubaController extends BaseController {

    private final static String PREFIX = "service/quba";

    @GetMapping("/manage")
    public String qubaManage(){
        return PREFIX+"/manage";
    }

    @GetMapping()
    public String user()
    {
        return PREFIX + "/quba";
    }

    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        return PREFIX + "/add";
    }

    @Autowired
    private IQubaService qubaService;

    @GetMapping("/getMyQuba")
    @ResponseBody
    public TableDataInfo getMyQuba(){
        startPage();
        return getDataTable(qubaService.getUserQuba());
    }

    @RequestMapping("/getAllQuba")
    @ResponseBody
    public TableDataInfo getAllQuba(){
        startPage();
        return getDataTable(qubaService.getAll());
    }

    @GetMapping("/getUserJoinedQuba")
    @ResponseBody
    public AjaxResult getUserJoinedQuba(){
        return success().put("data",qubaService.getHasJoinedQuba());
    }

    @GetMapping("/getQubaByName")
    @ResponseBody
    public TableDataInfo getQubaByName(String name){
        startPage();
        return getDataTable(qubaService.getQubaByName(name));
    }

    @GetMapping("/getQubaById")
    @ResponseBody
    public AjaxResult getQubaById(Long qubaId){
        return success().put("quba",qubaService.getQubaById(qubaId));
    }


    @GetMapping("/hasJoinedQuba")
    @ResponseBody
    public AjaxResult hasJoinedQuba(){
        return success().put("hasJoined",qubaService.isJoinedQuba());
    }


    @PostMapping("/joinQuba")
    @ResponseBody
    public AjaxResult joinQuba(Long qubaId){
      return toAjax(qubaService.insertQubaUser(ShiroUtils.getUserId(),qubaId));
    }

    @GetMapping("/getIsSigninToday")
    @ResponseBody
    public AjaxResult getIsSigninToday(Long qubaId){
        return toAjax(qubaService.getQubaSignInByUserId(ShiroUtils.getUserId(),qubaId));
    }

    @GetMapping("/signQuba")
    @ResponseBody
    public AjaxResult signQuba(Long qubaId){
        int isSigninToday = qubaService.getQubaSignInByUserId(ShiroUtils.getUserId(),qubaId);
        if (isSigninToday>0){
            return AjaxResult.error("重复签到");
        }else{
            return toAjax(qubaService.insertQubaSignIn(ShiroUtils.getUserId(),qubaId)) ;
        }
    }

    @GetMapping("/getQubaMember")
    @ResponseBody
    public TableDataInfo getQubaMember(Long qubaId)
    {   startPage();
        return getDataTable(qubaService.getQubaMemberByQubaId(qubaId));
    }

    @GetMapping("/getQubaOwner")
    @ResponseBody
    public AjaxResult getQubaOwner(Long qubaId){
        return success().put("owner",qubaService.getQubaOwner(qubaId));
    }

    @PostMapping("/getAllQubaUser")
    @ResponseBody
    public TableDataInfo getAllQubaUser(){
        startPage();
        return getDataTable(qubaService.getAllQubaUser());
    }

    @PostMapping("/getWaitExamine")
    @ResponseBody
    public TableDataInfo getAllWaitExamine(){
        startPage();
        return getDataTable(qubaService.getAllWaitExamine());
    }

    @PostMapping("/updateQubaUser")
    @ResponseBody
    public AjaxResult updateQubaUser(Long qubaUserId,Integer status){
        return toAjax(qubaService.updateQubaUserStatus(status,qubaUserId));
    }

    @PostMapping("/removeQuba")
    @ResponseBody
    public AjaxResult removeQuba(Long qubaId){
        return toAjax(qubaService.removeQuba(qubaId));
    }

    @PostMapping("/insertQuba")
    @ResponseBody
    public AjaxResult insertQuba(Quba quba){
        return toAjax(qubaService.insertQuba(quba));
    }

    @PostMapping("/updateQuba")
    @ResponseBody
    public AjaxResult updateQuba(Quba quba){
        return toAjax(qubaService.updateQuba(quba));
    }

}
