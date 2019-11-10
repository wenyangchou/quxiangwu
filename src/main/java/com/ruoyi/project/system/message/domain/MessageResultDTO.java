package com.ruoyi.project.system.message.domain;

import lombok.Data;

import java.util.Date;

/**
 * author:zwy
 * Date:2019-11-10
 * Time:20:36
 */
@Data
public class MessageResultDTO {

    private String senderName;

    private String senderAvatar;

    private String receiverName;

    private String receiverAvatar;

    private String msg;

    private Date time;
}
