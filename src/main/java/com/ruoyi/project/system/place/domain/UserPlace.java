package com.ruoyi.project.system.place.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.system.user.domain.User;
import lombok.Data;

import java.util.Date;

@Data
public class UserPlace extends BaseEntity {

    private Long id;

    private Date createTime;

    private Date modifyTime;

    private String place;

    private String cellphone;

    private Long userId;

    private User user;

    private String detail;

    private Integer isMaster;
}
