package com.ruoyi.project.system.message.service;

import com.ruoyi.project.system.message.domain.Message;
import com.ruoyi.project.system.message.domain.MessageDTO;
import com.ruoyi.project.system.message.domain.MessageResultDTO;

import java.util.List;

public interface IMessageService {

    List<Message> getByThingId(Long thingId);

    int addMessage(Message message);

    int addMessage(MessageDTO messageDTO);

    List<MessageResultDTO> getBySkuId(Long skuId);
}
