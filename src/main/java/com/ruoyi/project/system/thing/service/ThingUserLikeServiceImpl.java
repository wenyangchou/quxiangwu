package com.ruoyi.project.system.thing.service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.thing.mapper.ThingUserLikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author:zwy
 * Date:2019-10-02
 * Time:17:13
 */
@Service
public class ThingUserLikeServiceImpl implements IThingUserLikeService {

    @Autowired
    private ThingUserLikeMapper thingUserLikeMapper;

    @Override
    public Long getUserLikeByUserId(Long userId) {
        return thingUserLikeMapper.getUserLikeByUserId(userId);
    }

    @Override
    public Integer insertUserLike(Long userId, Long thingId) {
        return thingUserLikeMapper.insertUserLike(userId,thingId,0);
    }

    @Override
    public Integer deleteUserLike(Long likeId) {
        return thingUserLikeMapper.deleteLikeByLikeId(likeId);
    }


    @Override
    public Integer deleteUserLikeByUserIdAndThingId(Long userId, Long thingId) {
        return thingUserLikeMapper.deleteLikeByUserIdAndThingId(userId,thingId);
    }

    @Override
    public Integer toggleCollection(Long skuId) {
        Long userId = ShiroUtils.getUserId();
        Long userLikeId = thingUserLikeMapper.getUserLikeByUserIdAndThingId(userId,skuId);
        if (userLikeId == null){
            return insertUserLike(userId,skuId);
        }else{
            return deleteUserLikeByUserIdAndThingId(userId,skuId);
        }
    }
}
