package com.ruoyi.project.system.place.mapper;

import com.ruoyi.project.system.place.domain.UserPlace;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPlaceMapper {

    int insertUserPlace(UserPlace userPlace);

    UserPlace getMasterByUserId(Long userId);

    List<UserPlace> getUserPlace(Long userId);

    UserPlace getByPlaceId(Long placeId);

    int updateIsMasterByUserId(Long userId);

    int updateById(Long id);
}
