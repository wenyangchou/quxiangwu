package com.ruoyi.project.system.qualify.service;

import com.ruoyi.common.constant.QualifyConstant;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.qualify.domain.ConfirmHistoryDTO;
import com.ruoyi.project.system.qualify.domain.Qualify;
import com.ruoyi.project.system.qualify.mapper.QualifyMapper;
import com.ruoyi.project.system.user.domain.User;
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

    @Override
    public int addQualify(Qualify qualify) {

        User user = ShiroUtils.getUser();
        if (user.getIsQualified().equals( UserConstants.QUALIFIED)){
            return 0;
        }else{
            return qualifyMapper.insert(qualify);
        }
    }

    @Override
    public int updateQualify(Qualify qualify) {
        User user = ShiroUtils.getUser();
        if (user.getIsQualified().equals(UserConstants.QUALIFIED)){
            return 0;
        }else{
            return qualifyMapper.update(qualify);
        }
    }

    @Override
    public int setConfirm(Integer type, String imagePath) {
        User user = ShiroUtils.getUser();
        if (user.getIsQualified().equals(UserConstants.QUALIFIED)){
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
        return qualifyMapper.getConfirmHistory(type);
    }
}
