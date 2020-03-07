package com.ruoyi.project.system.user.domain;

import lombok.Data;

/**
 * author:zwy
 * Date:2020-03-07
 * Time:16:12
 */

@Data
public class FansDTO {

    private Long id;

    private String avatar;

    private String gender;

    private String userName;

    private String jobName;

    private Integer fansNum;

    private Boolean ifFocus;
}
