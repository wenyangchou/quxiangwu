package com.ruoyi.project.system.message.service;

import com.ruoyi.project.system.message.domain.Message;
import com.ruoyi.project.system.message.mapper.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> getByThingId(Long thingId) {
        List<Message> messages = messageMapper.getByThingId(thingId);
        messages.forEach(this::getChildren);
        return messages;
    }

    private void getChildren(Message message){
        List<Message> children = messageMapper.getByParentId(message.getId());
        if (children!=null&&children.size()!=0){
            message.setMessages(children);
            children.forEach(this::getChildren);
        }
    }

    @Override
    public int addMessage(Message message) {
        return messageMapper.insertMessage(message);
    }
}
