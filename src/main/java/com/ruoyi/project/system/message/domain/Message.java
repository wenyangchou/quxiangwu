package com.ruoyi.project.system.message.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import com.ruoyi.project.system.user.domain.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Message extends BaseEntity {

    private Long id;

    private Date createTime;

    private Date modifyTime;

    private Long thingId;

    private Long userId;

    private String content;

    private Long parentId;

    private List<Message> messages;

    private User user;
}
