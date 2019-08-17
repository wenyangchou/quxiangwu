package com.ruoyi.project.system.question.controller;

import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.question.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * author:zwy
 * Date:2019-08-14
 * Time:22:41
 */
@RestController
@RequestMapping("/service/question")
public class QuestionController extends BaseController {

    @Autowired
    private IQuestionService iQuestionService;

    @GetMapping("/getQuestions")
    public TableDataInfo getQuestions(){
        startPage();
        return getDataTable(iQuestionService.getAll());
    }
}
