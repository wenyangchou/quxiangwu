package com.ruoyi.project.system.place.service;

import com.ruoyi.project.system.place.domain.UserPlace;

import java.util.List;

/**
 * author:zwy
 * Date:2019-08-18
 * Time:01:11
 */
public interface IUserPlaceService {

    int addPlace(UserPlace userPlace);

    List<UserPlace> getUserPlace();

    int changeMaster(Long placeId);
}
