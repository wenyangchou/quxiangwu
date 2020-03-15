package com.ruoyi.project.system.quba.domain;

import lombok.Data;

import java.util.Date;

/**
 * author:zwy
 * Date:2020-03-15
 * Time:17:48
 */
@Data
public class QubaUserDTO {

    private Long qubaUserId;

    private Long userId;

    private Long qubaId;

    private String userName;

    private String qubaName;

    private Integer qubaStatus;

    private Integer userQualifyStatus;

    private Date createTime;

}
