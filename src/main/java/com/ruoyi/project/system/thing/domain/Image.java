package com.ruoyi.project.system.thing.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class Image extends BaseEntity {

    private Long id;

    private Date createTime;

    private Date modifyTime;

    private String imageUrl;

    private String imgPath;

    private Long thingId;
}
