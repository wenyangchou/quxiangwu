package com.ruoyi.project.system.invite.service;

import com.ruoyi.project.system.invite.mapper.InviteHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author:zwy
 * Date:2019-10-19
 * Time:18:07
 */
@Service
public class InviteHistoryServiceImpl implements IInviteHistoryService {

    @Autowired
    private InviteHistoryMapper inviteHistoryMapper;

    @Override
    public int addInviteHistory(Long invitorId, Long invitedId) {
        return inviteHistoryMapper.insert(invitorId,invitedId);
    }
}
