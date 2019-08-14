package com.ruoyi.project.system.thing.controller;

import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
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
@RequestMapping("/thing")
@Slf4j
public class ThingController  extends BaseController {

    @Autowired
    private IThingService thingService;


    @PostMapping("/addThing")
    public AjaxResult releaseThing(Thing thing){
       return toAjax(thingService.addThing(thing));
    }


    @PostMapping("/updateThingIndexImage")
    public AjaxResult updateThingIndexImage(Long thingId,Long imageId){
        return toAjax(thingService.updateThing(thingId,imageId));
    }

    @PostMapping("/uploadFile")
    public AjaxResult uploadFile( @RequestParam("file") MultipartFile file) {
        try {
            if (!file.isEmpty()){
                String filePath = FileUploadUtils.upload(file);
                return AjaxResult.success(filePath);
            }
            return AjaxResult.error("图片为空");
        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }

    }

}
