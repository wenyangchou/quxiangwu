package com.ruoyi.project.system.quba.service;

import com.ruoyi.project.system.quba.domain.Quba;
import com.ruoyi.project.system.quba.mapper.QubaMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * author:zwy
 * Date:2019-08-10
 * Time:16:00
 */
@Service
public class QubaServiceImpl implements IQubaService {

    @Autowired
    private QubaMapper qubaMapper;

    @Override
    public List<Quba> getAll() {
        return qubaMapper.getAll();
    }

    @Override
    public int insertQubaUser(Long userId, Long qubaId) {
        return qubaMapper.insertQubaUser(userId,qubaId);
    }

    @Override
    public int insertQubaSignIn(Long userId, Long qubaId) {
        return qubaMapper.insertQubaSignIn(userId,qubaId);
    }

    @Override
    public int getQubaSignInByUserId(Long userId, Long qubaId) {
        return qubaMapper.getQubaSignInByUserIdAndDate(userId,qubaId,new Date());
    }

    @Override
    public List<User> getQubaMemberByQubaId(Long qubaId) {
        return qubaMapper.getQubaMemberByQubaId(qubaId);
    }
}
