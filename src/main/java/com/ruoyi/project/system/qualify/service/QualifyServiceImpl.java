package com.ruoyi.project.system.qualify.service;

import com.ruoyi.common.constant.QualifyConstant;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.qualify.domain.ConfirmHistoryDTO;
import com.ruoyi.project.system.qualify.domain.ConfirmResultDTO;
import com.ruoyi.project.system.qualify.domain.Qualify;
import com.ruoyi.project.system.qualify.domain.UserQualifyDTO;
import com.ruoyi.project.system.qualify.mapper.QualifyMapper;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author:zwy
 * Date:2019-08-18
 * Time:01:41
 */
@Service
public class QualifyServiceImpl implements IQualifyService {

    @Autowired
    private QualifyMapper qualifyMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addQualify(Qualify qualify) {

        User user = ShiroUtils.getUser();
        if (user.getIsQualified().equals( UserConstants.EMPLOYEE_QUALIFIED)){
            return 0;
        }else{
            qualify.setUserId(ShiroUtils.getUserId());
            if (qualify.getQualifyType()==null){
                qualify.setQualifyType(QualifyConstant.TYPE_NORMAL);
            }
            qualify.setQualifyStatus(QualifyConstant.STATUS_WAIT_QUALIFY);
            return qualifyMapper.insert(qualify);
        }
    }

    @Override
    public int qualify(Long qualifyId, int qualifyStatus) {
        Qualify qualify = qualifyMapper.getById(qualifyId);
        qualify.setQualifyStatus(qualifyStatus);
        int result = qualifyMapper.update(qualify);

        if (qualify.getQualifyStatus().equals(com.ruoyi.project.system.qualify.constant.QualifyConstant.OK_QUALIFY) ){
            User user = userMapper.selectUserById(qualify.getUserId());
            user.setIsQualified(UserConstants.EMPLOYEE_QUALIFIED);
            userMapper.updateUser(user);
        }

        return result;
    }

    @Override
    public int updateQualify(Qualify qualify) {
        User user = ShiroUtils.getUser();
        if (user.getIsQualified().equals(UserConstants.EMPLOYEE_QUALIFIED)){
            user.setIsQualified(UserConstants.UNQUALIFIED);
            userMapper.updateUser(user);
        }
        qualify.setUserId(ShiroUtils.getUserId());
        if (StringUtils.isEmpty(qualify.getQualifyNegativeUrl()) ){
            qualify.setQualifyNegativeUrl(qualify.getQualifyPositiveUrl());
        }
        if (qualify.getQualifyType()==null){
            qualify.setQualifyType(QualifyConstant.TYPE_NORMAL);
        }
        qualify.setQualifyStatus(QualifyConstant.STATUS_WAIT_QUALIFY);
        return qualifyMapper.insert(qualify);
    }

    @Override
    public int setConfirm(Integer type, String imagePath) {
        User user = ShiroUtils.getUser();
        if (user.getIsQualified().equals(UserConstants.UNQUALIFIED)){
            Qualify qualify = new Qualify();
            qualify.setQualifyPositiveUrl(imagePath);
            qualify.setQualifyNegativeUrl(imagePath);
            qualify.setQualifyStatus(QualifyConstant.STATUS_WAIT_QUALIFY);
            qualify.setQualifyType(type);
            qualify.setUserId(ShiroUtils.getUserId());
            return qualifyMapper.insert(qualify);
        }else{
            return 0;
        }
    }

    @Override
    public List<ConfirmHistoryDTO> getConfirmHistory(Integer type) {
        return qualifyMapper.getConfirmHistory(type,ShiroUtils.getUserId());
    }

    @Override
    public ConfirmResultDTO getStatus(Integer type) {
        return qualifyMapper.getLastQualifyStatus(ShiroUtils.getUserId(),type);
    }

    @Override
    public List<UserQualifyDTO> getWaitQualify() {
        return qualifyMapper.getQualifyUserByStatus(QualifyConstant.STATUS_WAIT_QUALIFY);
    }

    @Override
    public List<UserQualifyDTO> getAllQualify() {
        return qualifyMapper.getQualifyUser();
    }
}
