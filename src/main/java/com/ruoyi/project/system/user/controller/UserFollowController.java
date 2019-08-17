package com.ruoyi.project.system.user.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author:zwy
 * Date:2019-08-17
 * Time:19:58
 */
@RestController
@RequestMapping("/service/user")
public class UserFollowController extends BaseController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getIsFollowed")
    public AjaxResult getIsFollowed(Long followerId){
        return toAjax(userService.isFollowed(followerId));
    }

    @PostMapping("/addFollow")
    public AjaxResult addFollow(Long followerId){
        return toAjax(userService.addFollow(followerId));
    }

    @PostMapping("/deleteFollow")
    public AjaxResult deleteFollow(Long followerId){
        return toAjax(userService.deleteFollow(followerId));
    }

    @GetMapping("/getUserFollower")
    public TableDataInfo getUserFollower(){
        return getDataTable(userService.getUserFollowers());
    }

    @GetMapping("/getUserFans")
    public TableDataInfo getUserFans(){
        return getDataTable(userService.getUserFans());
    }

}
