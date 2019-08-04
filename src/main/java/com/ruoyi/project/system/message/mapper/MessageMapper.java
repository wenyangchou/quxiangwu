package com.ruoyi.project.system.message.mapper;

import com.ruoyi.project.system.message.domain.Message;

import java.util.List;

public interface MessageMapper {

    List<Message> getByThingId(Long thingId);

    List<Message> getByParentId(Long messageId);

    int insertMessage(Message message);
}
