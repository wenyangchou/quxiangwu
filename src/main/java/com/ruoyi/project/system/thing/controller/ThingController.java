package com.ruoyi.project.system.thing.controller;

import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.thing.domain.Thing;
import com.ruoyi.project.system.thing.service.IThingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * author:zwy
 * Date:2019-08-10
 * Time:13:43
 */
@RestController
@RequestMapping("/service/thing")
@Slf4j
public class ThingController  extends BaseController {

    @Autowired
    private IThingService thingService;


    @PostMapping("/addThing")
    public AjaxResult releaseThing(@RequestBody Thing thing){
       return toAjax(thingService.addThing(thing));
    }




    @PostMapping("/updateThingIndexImage")
    public AjaxResult updateThingIndexImage(Long thingId,Long imageId){
        return toAjax(thingService.updateThing(thingId,imageId));
    }

    @PostMapping("/updateThingImage")
    public AjaxResult updateThingImage(String filePath,Long thingId){
        return toAjax(thingService.uploadFile(filePath,thingId));
    }

    @PostMapping("/uploadFile")
    public AjaxResult uploadFile( @RequestParam("file") MultipartFile file) {
        try {
            if (!file.isEmpty()){
                String filePath = FileUploadUtils.upload(file);
                return AjaxResult.success().put("filePath",filePath);
            }
            return AjaxResult.error("图片为空");
        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }
    }

    @GetMapping("/getLatestThing")
    public TableDataInfo getByLatest(){
        startPage();
        return getDataTable(thingService.getLatestThingDTO());
    }

    @GetMapping("/getLatestThingByTypeId")
    public TableDataInfo getByTypeId(Long typeId){
        startPage();
        return getDataTable(thingService.getLatestByTypeId(typeId));
    }

    @GetMapping("/getUserThing")
    public TableDataInfo getUserThing(Integer status){
        startPage();
        return getDataTable(thingService.getUserThingByStatus(status));
    }

    @GetMapping("/getUserBuy")
    public TableDataInfo getUserBuy(){
        startPage();
        return getDataTable(thingService.getUserBuy());
    }

}
