package com.ruoyi.project.system.type.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.type.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author:zwy
 * Date:2019-08-17
 * Time:21:17
 */
@RestController
@RequestMapping("/service/type")
public class TypeController extends BaseController {

    @Autowired
    private ITypeService typeService;

    @GetMapping("/getAll")
    public TableDataInfo getAllType(){
        startPage();
        return getDataTable(typeService.getAllTypes());
    }


}
