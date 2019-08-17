package com.ruoyi.project.system.place.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.place.domain.UserPlace;
import com.ruoyi.project.system.place.service.IUserPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author:zwy
 * Date:2019-08-18
 * Time:01:15
 */

@RestController
@RequestMapping("/service/userPlace")
public class UserPlaceController extends BaseController {

    @Autowired
    private IUserPlaceService userPlaceService;


    @PostMapping("/addPlace")
    public AjaxResult addUserPlace(@RequestBody UserPlace userPlace){
        return toAjax(userPlaceService.addPlace(userPlace));
    }

    @PostMapping("/updateMasterPlace")
    public AjaxResult updateUserMaster(Long placeId){
        return toAjax(userPlaceService.changeMaster(placeId));
    }

    @GetMapping("/getUserPlace")
    public TableDataInfo getUserPlace(){
        startPage();
        return getDataTable(userPlaceService.getUserPlace());
    }
}
