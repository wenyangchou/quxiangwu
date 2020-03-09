package com.ruoyi.project.system.user.mapper;

import com.ruoyi.project.system.user.domain.FansDTO;
import com.ruoyi.project.system.user.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author:zwy
 * Date:2019-08-17
 * Time:19:43
 */
@Repository
public interface UserFollowMapper {

    Long getByUserIdAndFollowerId(@Param("userId") Long userId,@Param("followerId") Long followerId);

    int insertFollow(@Param("userId") Long userId,@Param("followerId") Long followerId);

    int deleteFollow(@Param("userId") Long userId,@Param("followerId") Long followerId);

    List<User> getUserFollower(Long userId);

    List<User> getUserFans(Long userId);

    Long userFansNumber(Long userId);

    Long userFollowNumber(Long userId);

    List<FansDTO> getUserFansDTO(Long userId);

    List<FansDTO> getUserFollowDTO(Long userId);
}
