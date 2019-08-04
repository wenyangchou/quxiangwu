package com.ruoyi.project.system.question.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class Question extends BaseEntity {

    private Long id;

    private Date createTime;

    private Date modifyTime;

    private String question;

    private String description;

    private String answer;
}
