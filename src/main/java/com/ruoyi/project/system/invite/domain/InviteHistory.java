package com.ruoyi.project.system.invite.domain;

import lombok.Data;

import java.util.Date;

/**
 * author:zwy
 * Date:2019-10-19
 * Time:17:53
 */
@Data
public class InviteHistory  {

    private Long id;

    private Date createTime;

    private Date modifyTime;

    private Long invitorId;

    private Long invitedId;
}
