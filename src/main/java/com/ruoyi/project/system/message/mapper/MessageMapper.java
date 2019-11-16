package com.ruoyi.project.system.message.mapper;

import com.ruoyi.project.system.message.domain.Message;
import com.ruoyi.project.system.message.domain.MessageResultDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper {

    List<Message> getByThingId(Long thingId);

    List<Message> getByParentId(Long messageId);

    List<MessageResultDTO> getResultByThingId(Long thingId);

    int insertMessage(Message message);

    int getCountByThingId(Long thingId);
}
