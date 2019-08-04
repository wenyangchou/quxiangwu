package com.ruoyi.project.system.message.service;

import com.ruoyi.project.system.message.domain.Message;

import java.util.List;

public interface IMessageService {

    List<Message> getByThingId(Long thingId);

    int addMessage(Message message);
}
