package com.ruoyi.project.system.invite.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * author:zwy
 * Date:2019-10-19
 * Time:17:56
 */
@Repository
public interface InviteHistoryMapper {

    int insert(@Param("invitorId") Long invitorId, @Param("invitedId") Long invitedId);
}
