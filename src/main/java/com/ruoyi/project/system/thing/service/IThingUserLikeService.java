package com.ruoyi.project.system.thing.service;

import com.ruoyi.project.system.thing.domain.ThingDTO;

import java.util.List;

/**
 * author:zwy
 * Date:2019-10-02
 * Time:17:09
 */
public interface IThingUserLikeService {

    Long getUserLikeByUserId(Long userId);

    Integer insertUserLike(Long userId,Long thingId);

    Integer deleteUserLike(Long likeId);

    Integer deleteUserLikeByUserIdAndThingId(Long userId,Long thingId);

    Integer toggleCollection(Long skuId);

    List<ThingDTO> getUserLikeThingDTO();
}
