package com.ruoyi.project.system.message.service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.message.domain.Message;
import com.ruoyi.project.system.message.domain.MessageDTO;
import com.ruoyi.project.system.message.domain.MessageResultDTO;
import com.ruoyi.project.system.message.mapper.MessageMapper;
import com.ruoyi.project.system.thing.domain.Thing;
import com.ruoyi.project.system.thing.mapper.ThingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private ThingMapper thingMapper;

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

    @Override
    public int addMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setContent(messageDTO.getMsg());
        message.setThingId(messageDTO.getSkuId());
        message.setParentId(0L);
        message.setUserId(ShiroUtils.getUserId());
        if (messageDTO.getTo()==null){
            Thing thing = thingMapper.getById(messageDTO.getSkuId());
            message.setReceiverId(thing.getUserId());
        }else{
            message.setReceiverId(messageDTO.getTo());
        }

        return messageMapper.insertMessage(message);
    }

    @Override
    public List<MessageResultDTO> getBySkuId(Long skuId) {
        return messageMapper.getResultByThingId(skuId);
    }
}
