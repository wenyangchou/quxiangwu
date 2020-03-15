package com.ruoyi.project.system.quba.mapper;

import com.ruoyi.project.system.quba.domain.Quba;
import com.ruoyi.project.system.quba.domain.QubaUserDTO;
import com.ruoyi.project.system.user.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * author:zwy
 * Date:2019-08-10
 * Time:15:25
 */
@Repository
public interface QubaMapper {

    List<Quba> getAll();

    Quba getById(Long id);

    int inserQuba(Quba quba);

    int insertQubaUser(@Param("userId") Long userId,@Param("qubaId") Long qubaId);

    int insertQubaSignIn(@Param("userId") Long userId,@Param("qubaId") Long qubaId);

    int getQubaSignInByUserIdAndDate(@Param("userId") Long userId,@Param("qubaId") Long qubaId,@Param("date") Date date);

    List<User> getQubaMemberByQubaId(Long qubaId);

    List<Quba> getByUserId(Long userId);

    int getUserJoined(Long userId);

    Long getUserJoinedQuba(Long userId);

    List<Quba> getByName(String name);

    int getByQubaIdAndUserId(@Param("qubaId") Long qubaId,@Param("userId") Long userId);

    int getQubaMemberCount(Long userId);

    List<String> getQubaUserAvatarTop3(Long qubaId);

    List<QubaUserDTO> getAllQubaUser();

    List<QubaUserDTO> getWaitExamine();

    int updateQubaUserStatusById(@Param("qubaUserId") Long qubaUserId,@Param("status") int status);

    int updateQubaUserStatusByUserId(@Param("userId") Long userId,@Param("status") int status);

    Long getUserIdByQubaUserId(Long qubaUserId);

}
