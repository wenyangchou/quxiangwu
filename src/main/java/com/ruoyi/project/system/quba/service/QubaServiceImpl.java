package com.ruoyi.project.system.quba.service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.quba.constant.QubaConstant;
import com.ruoyi.project.system.quba.domain.Quba;
import com.ruoyi.project.system.quba.domain.QubaMemberDTO;
import com.ruoyi.project.system.quba.domain.QubaUserDTO;
import com.ruoyi.project.system.quba.mapper.QubaMapper;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.mapper.UserMapper;
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

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Quba> getAll() {
        return qubaMapper.getAll();
    }

    @Override
    public Long isJoinedQuba() {
        return qubaMapper.getUserJoined(ShiroUtils.getUserId());
    }

    @Override
    public Long getHasJoinedQuba() {
        return qubaMapper.getUserJoinedQuba(ShiroUtils.getUserId());
    }

    @Override
    public int insertQubaUser(Long userId, Long qubaId) {

        if (isJoinedQuba()!=null){
            return 0;
        }

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

    @Override
    public List<Quba> getUserQuba() {
        return qubaMapper.getByUserId(ShiroUtils.getUserId());
    }

    @Override
    public User getQubaOwner(Long qubaId) {
        Quba quba = qubaMapper.getById(qubaId);
        return userMapper.selectUserById(quba.getOwnerId());
    }

    @Override
    public List<Quba> getQubaByName(String name) {
        return qubaMapper.getByName("%"+name+"%");
    }

    @Override
    public QubaMemberDTO getQubaById(Long qubaId) {
        Quba quba = qubaMapper.getById(qubaId);
        QubaMemberDTO qubaMemberDTO = new QubaMemberDTO();
        qubaMemberDTO.setName(quba.getName());
        qubaMemberDTO.setLogo(quba.getLogo());
        qubaMemberDTO.setDesc(quba.getDescription());

        int isJoined = qubaMapper.getByQubaIdAndUserId(qubaId,ShiroUtils.getUserId());
        if (isJoined==0){
            qubaMemberDTO.setStatus(QubaConstant.NOT_EXAMINE);
        }else {
            qubaMemberDTO.setStatus(QubaConstant.EXAMINED);
        }

        int count = qubaMapper.getQubaMemberCount(qubaId);
        qubaMemberDTO.setMemberNum(count);

        List<String> avatars = qubaMapper.getQubaUserAvatarTop3(qubaId);
        qubaMemberDTO.setAvatarList(avatars);

        return qubaMemberDTO;
    }

    @Override
    public List<QubaUserDTO> getAllQubaUser() {
        return qubaMapper.getAllQubaUser();
    }

    @Override
    public List<QubaUserDTO> getAllWaitExamine() {
        return qubaMapper.getWaitExamine();
    }

    @Override
    public int updateQubaUserStatus(Integer status, Long qubaUserId) {

        if (status== QubaConstant.EXAMINED){
            Long userId = qubaMapper.getUserIdByQubaUserId(qubaUserId);
            qubaMapper.updateQubaUserStatusByUserId(userId,status);
        }

        return qubaMapper.updateQubaUserStatusById(qubaUserId,status);
    }

    @Override
    public int removeQuba(Long qubaId) {
        return qubaMapper.removeQuba(qubaId);
    }

    @Override
    public int updateQuba(Quba quba) {
        return qubaMapper.updateQuba(quba);
    }

    @Override
    public int insertQuba(Quba quba) {
        return qubaMapper.insertQuba(quba);
    }
}
