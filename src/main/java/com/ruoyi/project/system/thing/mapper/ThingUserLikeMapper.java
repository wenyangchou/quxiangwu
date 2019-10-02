package com.ruoyi.project.system.thing.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * author:zwy
 * Date:2019-10-02
 * Time:16:58
 */
@Repository
public interface ThingUserLikeMapper {

    Long getUserLikeByUserId(Long userId);

    int insertUserLike(@Param("userId") Long userId,@Param("thingId") Long thingId,@Param("status") Integer status);

    int deleteLikeByLikeId(Long likeId);

    int deleteLikeByUserIdAndThingId(@Param("userId") Long userId,@Param("thingId") Long thingId);

    Long getUserLikeByUserIdAndThingId(@Param("userId") Long userId,@Param("thingId") Long thingId);
}
