package com.ruoyi.project.system.user.domain;

import lombok.Data;

/**
 * author:zwy
 * Date:2020-03-07
 * Time:17:02
 */
@Data
public class FollowDTO {

    private String avatar;

    private String userName;

    private Boolean ifFocus;

    private Boolean ifConfirm;

    private String permanentAdd;

    private String jobName;

    private Long fansNum;

    private Long followerNum;

    private String gender;

}
