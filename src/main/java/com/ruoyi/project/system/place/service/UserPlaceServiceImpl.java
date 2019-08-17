package com.ruoyi.project.system.place.service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.place.domain.UserPlace;
import com.ruoyi.project.system.place.mapper.UserPlaceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:zwy
 * Date:2019-08-18
 * Time:01:12
 */
@Service
public class UserPlaceServiceImpl implements IUserPlaceService {

    @Autowired
    private UserPlaceMapper userPlaceMapper;

    @Override
    public int addPlace(UserPlace userPlace) {
        return userPlaceMapper.insertUserPlace(userPlace);
    }

    @Override
    public List<UserPlace> getUserPlace() {
        Long userId = ShiroUtils.getUserId();
        return userPlaceMapper.getUserPlace(userId);
    }

    @Override
    public int changeMaster(Long placeId) {
        Long userId = ShiroUtils.getUserId();
        userPlaceMapper.updateIsMasterByUserId(userId);
        return userPlaceMapper.updateById(placeId);
    }
}
