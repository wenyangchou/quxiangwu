package com.ruoyi.project.system.qualify.service;

import com.ruoyi.project.system.qualify.domain.ConfirmHistoryDTO;
import com.ruoyi.project.system.qualify.domain.ConfirmResultDTO;
import com.ruoyi.project.system.qualify.domain.Qualify;
import com.ruoyi.project.system.qualify.domain.UserQualifyDTO;

import java.util.List;

/**
 * author:zwy
 * Date:2019-08-18
 * Time:01:40
 */
public interface IQualifyService {

    int addQualify(Qualify qualify);

    int updateQualify(Qualify qualify);

    int setConfirm(Integer type,String imagePath);

    List<ConfirmHistoryDTO> getConfirmHistory(Integer type);

    ConfirmResultDTO getStatus(Integer type);

    List<UserQualifyDTO> getWaitQualify();

    List<UserQualifyDTO> getAllQualify();

}
