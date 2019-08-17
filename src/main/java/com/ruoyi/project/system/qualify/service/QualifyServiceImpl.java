package com.ruoyi.project.system.qualify.service;

import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.qualify.domain.Qualify;
import com.ruoyi.project.system.qualify.mapper.QualifyMapper;
import com.ruoyi.project.system.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (user.getIsQualified()==1){
            return 0;
        }else{
            return qualifyMapper.insert(qualify);
        }
    }

    @Override
    public int updateQualify(Qualify qualify) {
        User user = ShiroUtils.getUser();
        if (user.getIsQualified()==1){
            return 0;
        }else{
            return qualifyMapper.update(qualify);
        }
    }
}
