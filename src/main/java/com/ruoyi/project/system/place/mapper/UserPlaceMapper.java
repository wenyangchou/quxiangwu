package com.ruoyi.project.system.place.mapper;

import com.ruoyi.project.system.place.domain.UserPlace;

public interface UserPlaceMapper {

    int insertUserPlace(UserPlace userPlace);

    UserPlace getMasterByUserId(Long userId);
}
