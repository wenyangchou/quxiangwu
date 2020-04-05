package com.ruoyi.project.system.qualify.domain;

import lombok.Data;

import java.util.Date;

/**
 * author:zwy
 * Date:2020-03-11
 * Time:18:35
 */
@Data
public class UserQualifyDTO {

    private Long qualifyId;

    private Long userId;

    private String openId;

    private String userName;

    private String birthday;

    private String phone;

    private String gender;

    private String place;

    private Integer status;

    private String qualifyPositiveUrl;

    private String qualifyNegativeUrl;

    private Date createTime;

    private Integer qualifyType;

}
