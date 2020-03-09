package com.ruoyi.project.system.user.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * author:zwy
 * Date:2020-03-07
 * Time:14:07
 */
@Data
public class UserInfoDTO {

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    private Date birthday;

    private String permanentAdd;

    private String industry;

    private String jobName;

    private String education;

    private String gender;


}
