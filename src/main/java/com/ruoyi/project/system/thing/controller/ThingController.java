package com.ruoyi.project.system.thing.controller;

import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.domain.AjaxResult;
import lombok.extern.slf4j.Slf4j;
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
public class ThingController {

//    private

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
