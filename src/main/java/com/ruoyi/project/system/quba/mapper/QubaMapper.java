package com.ruoyi.project.system.quba.mapper;

import com.ruoyi.project.system.quba.domain.Quba;
import com.ruoyi.project.system.user.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * author:zwy
 * Date:2019-08-10
 * Time:15:25
 */
public interface QubaMapper {

    List<Quba> getAll();

    int insertQubaUser(@Param("userId") Long userId,@Param("qubaId") Long qubaId);

    int insertQubaSignIn(@Param("userId") Long userId,@Param("qubaId") Long qubaId);

    int getQubaSignInByUserIdAndDate(@Param("userId") Long userId,@Param("qubaId") Long qubaId,@Param("date") Date date);

    List<User> getQubaMemberByQubaId(Long qubaId);
}
