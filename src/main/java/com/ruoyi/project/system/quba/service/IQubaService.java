package com.ruoyi.project.system.quba.service;

import com.ruoyi.project.system.quba.domain.Quba;
import com.ruoyi.project.system.user.domain.User;

import java.util.List;

/**
 * author:zwy
 * Date:2019-08-10
 * Time:15:59
 */
public interface IQubaService {

    List<Quba> getAll();

    boolean isJoinedQuba();

    int insertQubaUser( Long userId, Long qubaId);

    int insertQubaSignIn( Long userId,Long qubaId);

    int getQubaSignInByUserId( Long userId,Long qubaId);

    List<User> getQubaMemberByQubaId(Long qubaId);

    List<Quba> getUserQuba();

    User getQubaOwner(Long qubaId);

    List<Quba> getQubaByName(String name);

}
